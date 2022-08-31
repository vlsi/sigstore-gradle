import me.champeau.gradle.igp.gitRepositories

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "sigstore-gradle"

plugins {
    `gradle-enterprise`
    id("de.fayard.refreshVersions") version "0.40.2"
    id("me.champeau.includegit") version "0.1.5"
}

val isCiServer = System.getenv().containsKey("CI")

if (isCiServer) {
    gradleEnterprise {
        buildScan {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            tag("CI")
        }
    }
}

// For some reason, build-logic-commons produces
includeBuild("build-logic-commons")
includeBuild("build-logic")

include("sigstore-testkit")
include("sigstore-sign-plugin")
include("sigstore-sign-base-plugin")

gitRepositories {
    include("sigstore-java") {
        uri.set("https://github.com/sigstore/sigstore-java.git")
        commit.set("692c8b9fc063f68a3c4946f61c859832904a6e2c")
    }
}
