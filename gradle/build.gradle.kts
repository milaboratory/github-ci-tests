plugins {
    java
    application
    id("com.bmuschko.docker-java-application") version "7.1.0"
}

group "com.milaboratory.helloworld"
version "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

application {
    applicationName = "HelloWorld"
    mainClass.set("com.milaboratory.helloworld.Main")
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
