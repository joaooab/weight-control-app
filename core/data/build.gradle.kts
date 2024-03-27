plugins {
    alias(libs.plugins.weightcontrol.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.br.weightcontrol.core.data"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
    implementation(libs.koin.android)
    implementation(libs.kotlinx.datetime)
}