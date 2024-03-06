plugins {
    alias(libs.plugins.weightcontrol.android.feature)
    alias(libs.plugins.weightcontrol.android.library.compose)
}

android {
    namespace = "com.br.weightcontrol.profile"
}

dependencies {
    implementation(project(":core:data"))
}