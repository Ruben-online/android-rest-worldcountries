# 🌍 World Countries

Aplicación móvil Android Nativa desarrollada con Kotlin y Jetpack Compose que permite consultar los países y territorios del mundo, mostrando sus nombres en español y sus respectivas banderas mediante el consumo de una API REST.

## 📖 Descripción

World Countries es una aplicación desarrollada como parte de una actividad universitaria, cuyo objetivo es implementar el consumo de un servicio REST dentro de una aplicación Android Nativa.

La aplicación cuenta con una interfaz moderna, minimalista e intuitiva que permite consultar 250 países y territorios, organizados alfabéticamente.

## ✨ Funcionalidades

- Pantalla de inicio con información del estudiante.
- Consumo de una API REST mediante peticiones HTTP GET.
- Consulta de 250 países y territorios.
- Visualización de banderas reales.
- Nombres de los países en español.
- Ordenamiento alfabético de A a Z.
- Desplazamiento vertical del listado.
- Navegación entre pantallas.
- Indicador de carga.
- Manejo de errores de conexión.
- Opción para reintentar la consulta.
- Enlace a la página oficial de REST Countries.

## 🛠️ Tecnologías utilizadas

| Tecnología | Descripción |
|---|---|
| Android Studio | Entorno de desarrollo integrado. |
| Kotlin | Lenguaje de programación. |
| Jetpack Compose | Desarrollo de interfaces gráficas nativas. |
| Material Design 3 | Componentes y diseño visual. |
| HttpURLConnection | Consumo del servicio REST. |
| JSON | Procesamiento de los datos recibidos. |
| Kotlin Coroutines | Gestión de operaciones asíncronas. |
| Gradle | Compilación y generación del APK. |
| Git y GitHub | Control de versiones. |

## 🌐 Servicio REST

La aplicación utiliza REST Countries v3.1 mediante la instancia alternativa de Conventus.

**Servicio original:**

https://restcountries.com/

**Endpoint utilizado:**

https://restcountries.conventus.de/v3.1/all?fields=name,flags,translations

### Nota sobre el servicio REST

Debido a la descontinuación de la versión v3.1 del servidor original de REST Countries, se utiliza una instancia alternativa compatible con dicha versión.

Esta permite consultar la información de los países sin necesidad de utilizar claves de acceso, autenticación o un backend adicional.

Los datos se obtienen dinámicamente mediante peticiones HTTP y no se encuentran almacenados de forma estática dentro de la aplicación.

## 📱 Pantallas de la aplicación

### Home

Pantalla principal que presenta:

- Nombre de la aplicación.
- Información del estudiante.
- Número de carnet.
- Botón para cargar los países.
- Enlace al servicio REST Countries.

### Países

Pantalla que presenta:

- Cantidad de países y territorios obtenidos.
- Listado ordenado alfabéticamente.
- Nombre de cada país en español.
- Bandera correspondiente.
- Botón para regresar a la pantalla principal.

## 📂 Estructura del proyecto

```text
android-rest-worldcountries/
├── apk/
│   └── WorldCountries.apk
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/worldcountries/
│           │   ├── MainActivity.kt
│           │   ├── data/
│           │   │   ├── Country.kt
│           │   │   └── CountryService.kt
│           │   └── ui/
│           │       ├── screens/
│           │       │   ├── HomeScreen.kt
│           │       │   └── CountriesScreen.kt
│           │       └── theme/
│           │           ├── Color.kt
│           │           ├── Theme.kt
│           │           └── Type.kt
│           ├── res/
│           └── AndroidManifest.xml
├── gradle/
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

## 📥 Descargar APK

La aplicación se encuentra disponible como archivo APK para su instalación directa en dispositivos Android.

**[Descargar WorldCountries.apk](https://github.com/Ruben-online/android-rest-worldcountries/raw/main/apk/WorldCountries.apk)**

### Requisitos

- Android 7.0 o superior.
- Conexión a internet.
- Permiso para instalar aplicaciones desde la fuente de descarga, si Android lo solicita.

### Instalación

1. Descargar el archivo APK.
2. Abrir el archivo desde un dispositivo Android.
3. Autorizar la instalación si el dispositivo lo solicita.
4. Instalar la aplicación.
5. Abrir World Countries.
6. Presionar el botón «Cargar».

## 💻 Ejecutar el proyecto localmente

Clonar el repositorio:

```bash
git clone https://github.com/Ruben-online/android-rest-worldcountries.git
```

Abrir el proyecto desde Android Studio y realizar la sincronización de Gradle.

Para generar el APK desde la terminal:

```bash
./gradlew assembleDebug
```

El archivo generado estará disponible en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

También se puede ejecutar la aplicación desde Android Studio utilizando un emulador o un dispositivo Android físico.

## 👨‍💻 Autor

**Rubén Fernando Espinoza Fernández**

- Carnet: 25008248
- GitHub: [Ruben-online](https://github.com/Ruben-online)

## 📌 Información del proyecto

- Nombre: World Countries.
- Versión: 1.0.
- Plataforma: Android.
- Tipo de aplicación: Android Nativa.
- Finalidad: Académica.
- Año: 2026.

---

Desarrollado con Kotlin y Jetpack Compose.