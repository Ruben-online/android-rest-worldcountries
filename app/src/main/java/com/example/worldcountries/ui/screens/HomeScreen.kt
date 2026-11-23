package com.example.worldcountries.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldcountries.ui.theme.*

@Composable
fun HomeScreen(
    onLoadCountries: () -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Encabezado
        Text(
            text = "Home",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Icono principal
        Box(
            modifier = Modifier
                .size(112.dp)
                .background(LightBlue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🌍",
                fontSize = 60.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Titulo de la aplicacion
        Text(
            text = "World Countries",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Explora los países del mundo y descubre sus banderas.",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Informacion del estudiante
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            border = BorderStroke(1.dp, BorderColor),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "ESTUDIANTE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    color = PrimaryBlue,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Rubén Fernando Espinoza Fernández",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(color = BorderColor)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "NÚMERO DE CARNET",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    color = PrimaryBlue,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "25008248",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Boton principal
        Button(
            onClick = onLoadCountries,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue,
                contentColor = White
            )
        ) {
            Text(
                text = "Cargar",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fuente de informacion
        Text(
            text = "Información proporcionada por el servicio REST:",
            fontSize = 12.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Enlace oficial de REST Countries
        Text(
            text = "https://restcountries.com/",
            fontSize = 12.sp,
            color = PrimaryBlue,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                uriHandler.openUri("https://restcountries.com/")
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 393,
    heightDp = 852
)
@Composable
fun HomeScreenPreview() {
    WorldCountriesTheme {
        HomeScreen()
    }
}