plugins {
    id("weightcontrol.android.library")
    id("weightcontrol.android.library.compose")
}

android {
    namespace = "com.br.weightcontrol.core.ui"
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:model"))
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)
    implementation(libs.chart.core)
    implementation(libs.chart.compose)
    implementation(libs.chart.compose.m3)
    implementation(libs.kotlinx.datetime)
}