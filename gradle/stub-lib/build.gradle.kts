plugins {
    id("java-library")
    id("maven-publish")
    id("com.bmuschko.docker-java-application")
    id("com.palantir.git-version")
}

val miGitHubMavenUser: String by project
val miGitHubMavenToken: String by project

val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
val gitDetails = versionDetails()

fun boolProperty(name: String): Boolean {
    return ((properties[name] as String?) ?: "false").toBoolean()
}

val isMiCi: Boolean = boolProperty("mi-ci")
val isRelease: Boolean = boolProperty("mi-release")

val group: String = "com.milaboratory.stub"
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

publishing {
    repositories {
        if (isMiCi) {
            maven {
                name = "GitHubPackages"

                url = if (isRelease) {
                    uri("https://maven.pkg.github.com/milaboratory/releases")
                } else {
                    uri("https://maven.pkg.github.com/milaboratory/private")
                }

                credentials {
                    username = miGitHubMavenUser
                    password = miGitHubMavenToken
                }
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = group
            artifactId = "lib"
            version = version

            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
