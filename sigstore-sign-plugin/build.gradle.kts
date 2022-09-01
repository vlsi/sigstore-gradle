plugins {
    id("buildlogic.kotlin-dsl-published-plugin")
    id("buildlogic.test-junit5")
}

description = "Gradle plugin to that automatically signs all Publications in Sigstore"

dependencies {
    implementation(project(":sigstore-sign-base-plugin"))

    testImplementation(project(":sigstore-testkit"))
}

pluginBundle {
    website = "https://github.com/vlsi/sigstore-gradle-draft"
    vcsUrl = "https://github.com/vlsi/sigstore-gradle-draft.git"
    tags = listOf("sigstore", "sign")
}

gradlePlugin {
    plugins {
        named("dev.sigstore.sign") {
            displayName = "Sign artifacts via Sigstore"
            description = "Plugin for signing artifacts via Sigstore"
        }
    }
}
configure<PublishingExtension> {
    repositories {
        maven(layout.buildDirectory.dir("tmp-repo")) {
            name = "tmp"
        }
    }
}
