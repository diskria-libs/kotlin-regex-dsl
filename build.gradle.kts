import io.github.diskria.projektor.publishing.MavenCentral

plugins {
    `maven-publish`
    signing
    alias(libs.plugins.projektor)
    alias(libs.plugins.build.config)
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.kotlin.utils)
}

projekt {
    publishingTarget = MavenCentral

    kotlinLibrary()
}
