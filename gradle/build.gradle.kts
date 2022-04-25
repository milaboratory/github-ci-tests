plugins {
    java
    id("com.bmuschko.docker-java-application") version "7.3.0" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}