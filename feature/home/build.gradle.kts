plugins {
    id("weightcontrol.android.feature")
    id("weightcontrol.android.library.compose")
}

android {
    namespace = "com.br.weightcontrol.home"
}

dependencies {
    implementation(project(":feature:bmi"))
    implementation(project(":feature:goal"))
    implementation(project(":feature:track"))
    implementation(libs.kotlinx.datetime)
}
