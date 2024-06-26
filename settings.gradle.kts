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
include("app")
include(":core:data")
include(":core:database")
include(":core:datastore")
include(":core:design-system")
include(":core:domain")
include(":core:model")
include(":core:ui")
include(":core:util")
include(":core:test")
include(":feature:bmi")
include(":feature:home")
include(":feature:history")
include(":feature:settings")
include(":feature:track")
include(":feature:onboarding")
include(":feature:profile")
include(":feature:goal")
include(":feature:ads")
