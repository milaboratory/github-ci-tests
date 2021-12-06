plugins {
    java
    application
    id("com.bmuschko.docker-java-application")
    id("com.palantir.git-version")
}

val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
val gitDetails = versionDetails()

group = "com.milaboratory.stub.app"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

application {
    applicationName = "StubApp"
    mainClass.set("com.milaboratory.stub.app.Main")
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
