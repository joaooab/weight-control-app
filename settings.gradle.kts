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
include(":core:data")
include(":core:database")
include(":core:design-system")
include(":core:model")
include(":core:ui")
include(":core:util")
include(":core:test")
include(":feature:bmi")
include(":feature:home")
include(":feature:history")
include(":feature:settings")
