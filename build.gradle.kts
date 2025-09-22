import io.github.diskria.projektor.extensions.configureLibrary

plugins {
    `maven-publish`
    signing
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.projektor)
}

dependencies {
    implementation(libs.kotlin.utils)
}

configureLibrary()
