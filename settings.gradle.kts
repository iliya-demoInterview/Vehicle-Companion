pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Vehicle Companion"
include(":app")
include(":db")
include(":model")
include(":data:vehicle")
include(":feature:garage")
include(":feature:create-vehicle")
include(":feature:edit-vehicle")
include(":feature:shared")
include(":network")
include(":feature:poi")
include(":data:poi")
include(":feature:favourites")
