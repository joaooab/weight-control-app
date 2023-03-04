plugins {
    id("weightcontrol.android.library")
}

android {
    namespace = "com.br.weightcontrol.core.test"
}

dependencies {
    api(libs.junit4)
    api(libs.androidx.test.core)
    api(libs.kotlinx.coroutines.test)
}