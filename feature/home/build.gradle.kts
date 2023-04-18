plugins {
    id("weightcontrol.android.feature")
    id("weightcontrol.android.library.compose")
}

android {
    namespace = "com.br.weightcontrol.home"
}

dependencies {
    implementation(project(":feature:bmi"))
    implementation(libs.kotlinx.datetime)
}
