plugins {
    alias(libs.plugins.weightcontrol.android.feature)
    alias(libs.plugins.weightcontrol.android.library.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.br.weightcontrol.ads"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(libs.admob)
}