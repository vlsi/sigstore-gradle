import buildlogic.plugin

plugins {
    id("buildlogic.kotlin-dsl-gradle-plugin")
}

group = "dev.sigstore.sigstore-gradle.buildlogic"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(project(":basics"))
    implementation(project(":jvm"))
    implementation("dev.sigstore.sigstore-gradle:gradle-plugin")
    implementation(plugin("com.gradle.plugin-publish", "0.14.0"))
    implementation(plugin("io.github.gradle-nexus.publish-plugin", "1.1.0"))
}
