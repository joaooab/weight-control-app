plugins {
    id("weightcontrol.android.feature")
    id("weightcontrol.android.library.compose")
}

android {
    namespace = "com.br.weightcontrol.settings"
}

dependencies {
    implementation(project(":feature:goal"))
}
