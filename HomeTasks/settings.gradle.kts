pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version "8.3.0"
        id("com.google.gms.google-services") version "4.4.0"
        id("org.jetbrains.kotlin.android") version "1.9.22"

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HomeTasks"
include(":app")
