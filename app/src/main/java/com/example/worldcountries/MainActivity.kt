package com.example.worldcountries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.worldcountries.ui.screens.HomeScreen
import com.example.worldcountries.ui.theme.WorldCountriesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            WorldCountriesTheme {
                HomeScreen()
            }
        }
    }
}