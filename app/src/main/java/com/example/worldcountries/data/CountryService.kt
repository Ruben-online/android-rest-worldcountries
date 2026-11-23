package com.example.worldcountries.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.text.Collator
import java.util.Locale

object CountryService {

    private const val API_URL =
        "https://restcountries.conventus.de/v3.1/all?fields=name,flags,translations"

    suspend fun getCountries(): List<Country> =
        withContext(Dispatchers.IO) {

            val connection = URL(API_URL)
                .openConnection() as HttpURLConnection

            try {
                connection.requestMethod = "GET"
                connection.connectTimeout = 15000
                connection.readTimeout = 15000
                connection.setRequestProperty(
                    "Accept",
                    "application/json"
                )

                if (connection.responseCode != 200) {
                    throw Exception(
                        "Error del servidor: ${connection.responseCode}"
                    )
                }

                val response = connection.inputStream
                    .bufferedReader(Charsets.UTF_8)
                    .use { it.readText() }

                val jsonArray = JSONArray(response)

                val countries = mutableListOf<Country>()

                for (i in 0 until jsonArray.length()) {

                    val countryJson = jsonArray.getJSONObject(i)

                    val originalName = countryJson
                        .getJSONObject("name")
                        .getString("common")

                    val spanishName = countryJson
                        .optJSONObject("translations")
                        ?.optJSONObject("spa")
                        ?.optString("common")
                        ?.takeIf { it.isNotBlank() }
                        ?: originalName

                    val flagUrl = countryJson
                        .getJSONObject("flags")
                        .getString("png")

                    countries.add(
                        Country(
                            name = spanishName,
                            flagUrl = flagUrl
                        )
                    )
                }

                val collator = Collator.getInstance(
                    Locale.forLanguageTag("es")
                )

                countries.sortedWith(
                    compareBy(collator) { it.name }
                )

            } finally {
                connection.disconnect()
            }
        }
}