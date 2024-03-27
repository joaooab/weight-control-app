plugins {
    alias(libs.plugins.weightcontrol.android.feature)
    alias(libs.plugins.weightcontrol.android.library.compose)
}

android {
    namespace = "com.br.weightcontrol.home"
}

dependencies {
    implementation(project(":feature:bmi"))
    implementation(project(":feature:goal"))
    implementation(project(":feature:track"))
    implementation(project(":feature:ads"))
    implementation(libs.kotlinx.datetime)
}
