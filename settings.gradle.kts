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

rootProject.name = "GrowwStocks"
include(":app")
include(":feature:home")
include(":feature:product_detail")
include(":feature:watchlist")
include(":feature:product_list")
include(":core:common")
include(":core:ui")
include(":core:network")
include(":core:database")
