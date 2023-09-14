plugins {
    id("weightcontrol.android.library")
}

android {
    namespace = "com.br.weightcontrol.core.util"
}

dependencies {
    api(libs.kotlinx.datetime)

    testImplementation(project(":core:test"))
}