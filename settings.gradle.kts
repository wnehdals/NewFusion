pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "NewsFusion"
include(":app")
include(":core:data")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":core:navigation")

include(":feature:main")
include(":core:testing")
include(":feature:news")
include(":baselineprofile")
