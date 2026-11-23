package com.example.worldcountries.ui.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldcountries.data.Country
import com.example.worldcountries.ui.theme.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun CountriesScreen(
    countries: List<Country> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBack: () -> Unit = {},
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .safeDrawingPadding()
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {

        // Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = "← Volver",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryBlue
                )
            }

            Text(
                text = "Países",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Titulo principal
        Text(
            text = "Explora el mundo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (isLoading || errorMessage != null) {
                "Descubre los países y sus banderas."
            } else {
                "${countries.size} países encontrados"
            },
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        when {

            // Estado de carga
            isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = PrimaryBlue
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Cargando países...",
                        fontSize = 15.sp,
                        color = TextSecondary
                    )
                }
            }

            // Estado de error
            errorMessage != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No se pudieron cargar los países",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = errorMessage,
                        fontSize = 14.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onRetry,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Reintentar")
                    }
                }
            }

            // Lista de paises
            countries.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(countries) { country ->
                        CountryCard(country)
                    }
                }
            }

            // Estado vacio
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron países.",
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun CountryCard(country: Country) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        border = BorderStroke(1.dp, BorderColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            CountryFlag(
                country = country
            )

            Spacer(modifier = Modifier.width(18.dp))

            Text(
                text = country.name,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }
    }
}

@Composable
private fun CountryFlag(country: Country) {

    var bitmap by remember(country.flagUrl) {
        mutableStateOf<Bitmap?>(null)
    }

    LaunchedEffect(country.flagUrl) {

        if (country.flagUrl.isNotBlank()) {
            try {
                bitmap = withContext(Dispatchers.IO) {

                    val connection = URL(country.flagUrl)
                        .openConnection() as HttpURLConnection

                    try {
                        connection.connectTimeout = 10000
                        connection.readTimeout = 10000

                        connection.inputStream.use { stream ->
                            BitmapFactory.decodeStream(stream)
                        }

                    } finally {
                        connection.disconnect()
                    }
                }

            } catch (exception: CancellationException) {
                throw exception

            } catch (exception: Exception) {
                bitmap = null
            }
        }
    }

    Box(
        modifier = Modifier
            .size(width = 80.dp, height = 52.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(LightBlue),
        contentAlignment = Alignment.Center
    ) {

        val flag = bitmap

        if (flag != null) {
            Image(
                bitmap = flag.asImageBitmap(),
                contentDescription = "Bandera de ${country.name}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        } else {
            Text(
                text = "🌐",
                fontSize = 26.sp
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 393,
    heightDp = 852
)
@Composable
fun CountriesScreenPreview() {

    val exampleCountries = listOf(
        Country("Alemania", ""),
        Country("Argentina", ""),
        Country("Brasil", ""),
        Country("Canadá", ""),
        Country("España", ""),
        Country("Estados Unidos", ""),
        Country("Francia", ""),
        Country("Guatemala", ""),
        Country("Italia", ""),
        Country("México", "")
    )

    WorldCountriesTheme {
        CountriesScreen(
            countries = exampleCountries
        )
    }
}