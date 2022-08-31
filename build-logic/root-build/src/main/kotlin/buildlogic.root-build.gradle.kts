import io.github.gradlenexus.publishplugin.NexusPublishExtension

plugins {
    base
    id("io.github.gradle-nexus.publish-plugin")
}

// io.github.gradle-nexus.publish-plugin is not compatible with precompiled Gradle plugins,
// so the plugin is applied in the root project, and here we configure it
extensions.findByType<NexusPublishExtension>()?.apply {
    repositories {
        sonatype()
    }
}
