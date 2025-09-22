import io.github.diskria.projektor.extensions.configureProject

pluginManagement {
    repositories {
        mavenCentral()
        maven("https://diskria.github.io/projektor")
    }
}

plugins {
    id("io.github.diskria.projektor.settings") version "1.+"
}

configureProject()
