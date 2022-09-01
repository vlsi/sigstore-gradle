plugins {
    id("buildlogic.root-build")
    // It does not support participating in precompiled script plugins
    id("com.github.vlsi.stage-vote-release")
    // id("com.github.vlsi.stage-vote-release")
    // Load Kotlin Gradle Plugin to build script classpath to avoid loading it multiple times
    // Otherwise it results in the following warning
    /// The Kotlin Gradle plugin was loaded multiple times in different subprojects, which is not supported and may break the build.
    kotlin("jvm") apply false
}

val String.v: String get() = rootProject.extra["$this.version"] as String

val buildVersion = "project".v + releaseParams.snapshotSuffix

println("Building Sigstore Gradle plugin $buildVersion")

val isReleaseVersion = rootProject.releaseParams.release.get()

releaseParams {
    tlp.set("SigstoreGradle")
    organizationName.set("Sigstore")
    componentName.set("SigstoreGradle")
    prefixForProperties.set("s01")
    svnDistEnabled.set(false)
    sitePreviewEnabled.set(false)
    nexus {
        prodUrl.set(uri("https://s01.oss.sonatype.org"))
    }
    voteText.set {
        """
        ${it.componentName} v${it.version}-rc${it.rc} is ready for preview.

        Git SHA: ${it.gitSha}
        Staging repository: ${it.nexusRepositoryUri}
        """.trimIndent()
    }
}

allprojects {
    version = project.findProperty("project.version") as? String ?: rootProject.version
}
