plugins {
    `java-library`
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:_")
    testImplementation("org.assertj:assertj-core:_")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
