rootProject.name = "kotlin-regex-dsl"

fun RepositoryHandler.attachCommonRepositories() {
    mavenCentral()
    google()
}

fun RepositoryHandler.attachPluginRepositories() {
    gradlePluginPortal()
}

@Suppress("UnstableApiUsage")
fun setupRepositories() {
    dependencyResolutionManagement.repositories {
        attachCommonRepositories()
    }

    pluginManagement.repositories {
        attachCommonRepositories()
        attachPluginRepositories()
    }
}

setupRepositories()
