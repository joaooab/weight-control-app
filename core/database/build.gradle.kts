plugins {
    alias(libs.plugins.weightcontrol.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.br.weightcontrol.core.database"
}

dependencies {
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.koin.android)
    implementation(libs.kotlinx.datetime)
}