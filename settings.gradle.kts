import io.github.diskria.projektor.common.licenses.MIT

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://diskria.github.io/projektor")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("io.github.diskria.projektor.settings") version "3.+"
}

projekt {
    description = "A Kotlin DSL for building and working with regular expressions."
    version = "0.3.0"
    license = MIT

    kotlinLibrary()
}
