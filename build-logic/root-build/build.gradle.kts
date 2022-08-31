import buildlogic.plugin

plugins {
    id("buildlogic.kotlin-dsl-gradle-plugin")
}

group = "dev.sigstore.sigstore-gradle"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(plugin("io.github.gradle-nexus.publish-plugin", "1.1.0"))
}
