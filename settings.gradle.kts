pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "weightcontrol"
include("appcompose")
include(":core:design-system")
include(":feature:home")
include(":feature:history")
include(":feature:settings")
