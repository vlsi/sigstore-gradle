import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    id("buildlogic.java")
    id("com.github.vlsi.gradle-extensions")
    id("com.github.autostyle")
    kotlin("jvm")
}

java {
    withSourcesJar()
}

autostyle {
    kotlin {
        file("$rootDir/config/licenseHeader").takeIf { it.exists() }?.let {
            licenseHeader(it.readText())
        }
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}
