plugins {
    id("weightcontrol.android.library")
}

android {
    namespace = "com.br.weightcontrol.core.uitl"
}

dependencies {
    implementation(libs.kotlinx.datetime)
}