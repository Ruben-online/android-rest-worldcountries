package com.example.worldcountries

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.worldcountries.data.Country
import com.example.worldcountries.data.CountryService
import com.example.worldcountries.ui.screens.HomeScreen
import com.example.worldcountries.ui.screens.CountriesScreen
import com.example.worldcountries.ui.theme.WorldCountriesTheme
import kotlinx.coroutines.CancellationException

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Impide capturas y grabaciones de pantalla de la aplicacion.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        enableEdgeToEdge()

        setContent {
            WorldCountriesTheme {
                WorldCountriesApp()
            }
        }
    }
}

@Composable
fun WorldCountriesApp() {

    var showCountries by remember {
        mutableStateOf(false)
    }

    var countries by remember {
        mutableStateOf<List<Country>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    var retryCount by remember {
        mutableIntStateOf(0)
    }

    // Boton de retroceso del sistema Android
    BackHandler(enabled = showCountries) {
        showCountries = false
    }

    if (showCountries) {

        // Consultar la API al entrar o reintentar
        LaunchedEffect(retryCount) {

            isLoading = true
            errorMessage = null
            countries = emptyList()

            try {
                countries = CountryService.getCountries()

            } catch (exception: CancellationException) {
                throw exception

            } catch (exception: Exception) {
                errorMessage =
                    "Verifica tu conexión a internet e inténtalo nuevamente."

            } finally {
                isLoading = false
            }
        }

        CountriesScreen(
            countries = countries,
            isLoading = isLoading,
            errorMessage = errorMessage,
            onBack = {
                showCountries = false
            },
            onRetry = {
                retryCount++
            }
        )

    } else {

        HomeScreen(
            onLoadCountries = {
                countries = emptyList()
                errorMessage = null
                isLoading = true
                showCountries = true
            }
        )
    }
}