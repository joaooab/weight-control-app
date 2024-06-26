plugins {
    alias(libs.plugins.weightcontrol.android.library)
}

android {
    namespace = "com.br.weightcontrol.core.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:util"))
    implementation(libs.koin.android)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(project(":core:test"))
}