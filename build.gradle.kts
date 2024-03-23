plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    `maven-publish`
}

group = "txapar.com"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(libs.bundles.grpc)
    implementation(libs.bundles.required)
}

publishing {
    publications {
        create<MavenPublication>("myLibrary") {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
//        maven {
//            name = "myRepo"
//            url = uri(layout.buildDirectory.dir("repo"))
//        }
    }
}
