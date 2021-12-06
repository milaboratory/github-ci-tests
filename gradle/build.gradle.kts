plugins {
    java
    id("com.bmuschko.docker-java-application") version "7.1.0"
    id("com.palantir.git-version") version "0.12.3"
}

val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
val gitDetails = versionDetails()

group = "com.milaboratory.stub"
version = if (version != "unspecified") {
    version
} else if (gitDetails.commitDistance == 0) {
    gitDetails.lastTag
} else {
    "${gitDetails.lastTag}-${gitDetails.commitDistance}-${gitDetails.gitHash}"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

docker {
    javaApplication {
        baseImage.set("openjdk:11")
        images.set(listOf("$name:$version", "$name:latest"))
    }
}

tasks.test {
    useJUnitPlatform()
}
