plugins {
    id("buildlogic.kotlin")
    id("buildlogic.repositories")
}

dependencies {
    api("org.junit.jupiter:junit-jupiter:_")
    api("org.assertj:assertj-core:_")
    api(gradleTestKit())
}
