import buildlogic.cleanupMavenPom

plugins {
    id("java-library")
    id("maven-publish")
}

val repoUrl = "https://github.com/vlsi/sigstore-gradle"

publishing {
    publications.withType<MavenPublication> {
        // Use the resolved versions in pom.xml
        // Gradle might have different resolution rules, so we set the versions
        // that were used in Gradle build/test.
        versionMapping {
            usage(Usage.JAVA_RUNTIME) {
                fromResolutionResult()
            }
            usage(Usage.JAVA_API) {
                fromResolutionOf("runtimeClasspath")
            }
        }
        pom {
            cleanupMavenPom()
            name.set(
                (project.findProperty("artifact.name") as? String)
                    ?: project.name
            )
            description.set(project.description)
            inceptionYear.set("2017")
            url.set(repoUrl)
            organization {
                name.set("sigstore")
                url.set("https://sigstore.dev")
            }
            developers {
                developer {
                    name.set("Vladimir Sitnikov")
                    id.set("vlsi")
                    email.set("sitnikov.vladmir@gmail.com")
                }
            }
            issueManagement {
                system.set("GitHub Issues")
                url.set("$repoUrl/issues")
            }
            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            scm {
                connection.set("scm:git:$repoUrl.git")
                developerConnection.set("scm:git:$repoUrl.git")
                url.set(repoUrl)
                tag.set("HEAD")
            }
        }
    }
}
