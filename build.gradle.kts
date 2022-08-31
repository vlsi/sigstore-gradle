plugins {
    id("buildlogic.root-build")
    id("io.github.gradle-nexus.publish-plugin")
    // Load Kotlin Gradle Plugin to build script classpath to avoid loading it multiple times
    // Otherwise it results in the following warning
    /// The Kotlin Gradle plugin was loaded multiple times in different subprojects, which is not supported and may break the build.
    kotlin("jvm") apply false
}

group = "dev.sigstore.sigstore-gradle"
