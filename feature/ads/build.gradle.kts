plugins {
    id("weightcontrol.android.feature")
    id("weightcontrol.android.library.compose")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.br.weightcontrol.ads"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(libs.admob)
}