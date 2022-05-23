plugins {
    java
    application
    `maven-publish`
    id("com.bmuschko.docker-java-application")
}

val miGitHubMavenUser: String? by project
val miGitHubMavenToken: String? by project

fun boolProperty(name: String): Boolean {
    return ((properties[name] as String?) ?: "false").toBoolean()
}

val isMiCi: Boolean = boolProperty("mi-ci")
val isRelease: Boolean = boolProperty("mi-release")

group = "com.milaboratory.stub"
version = if (version != "unspecified") version else ""

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

publishing {
    repositories {
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/milaboratory/github-ci-tests")

            credentials {
                username = miGitHubMavenUser
                password = miGitHubMavenToken
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
