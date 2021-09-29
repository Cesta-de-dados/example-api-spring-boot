plugins {
    id("org.sonarqube") version "3.3"
    id("nu.studer.jooq") version "6.0.1"

    kotlin("jvm") version "1.5.31"
}

group = "com.example.foo"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")

    group = "com.example.foo"
    version = "1.0-SNAPSHOT"
}

sonarqube {
    properties {
        property("sonar.coverage.exclusions", "**/model/build/**")
    }
}