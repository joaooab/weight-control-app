plugins {
    id("weightcontrol.android.feature")
    id("weightcontrol.android.library.compose")
}

android {
    namespace = "com.br.weightcontrol.profile"
}

dependencies {
    implementation(project(":core:data"))
}