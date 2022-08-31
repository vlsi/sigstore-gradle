import buildlogic.embeddedKotlinDsl
import buildlogic.plugin

plugins {
    id("buildlogic.kotlin-dsl-gradle-plugin")
}

group = "dev.sigstore.sigstore-gradle.buildlogic2"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(embeddedKotlinDsl())
    implementation(plugin("com.github.vlsi.gradle-extensions", "1.82"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation(plugin("org.jetbrains.dokka", "1.4.32"))
    implementation(plugin("com.github.autostyle", "3.2"))
}
