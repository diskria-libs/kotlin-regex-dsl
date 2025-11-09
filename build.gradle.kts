plugins {
    alias(libs.plugins.projektor)
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.kotlin.utils)
}

projekt {
    kotlinLibrary {
        jvmTarget = JvmTarget.JVM_21
    }
}
