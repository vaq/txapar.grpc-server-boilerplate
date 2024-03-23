rootProject.name = "grpc-server-boilerplate"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {

            // ------------------------------ versions ------------------------------
            version("lombok", "1.18.20")
            version("slf4j-api", "1.7.32")
            version("testng", "7.4.0")
            version("grpc-protobuf", "1.62.2")
            version("grpc-stub", "1.62.2")
            version("grpc-netty-shaded", "1.62.2")
            version("protobuf-java", "3.25.3")
            version("javax-annotation-api", "1.3.1")

            // ------------------------------ commonly required ------------------------------
            library("org.projectlombok", "org.projectlombok", "lombok").versionRef("lombok") // lombok also requires a plugin in the java-conventions plugin
            library("slf4j-api", "org.slf4j", "slf4j-api").versionRef("slf4j-api")
            library("testng", "org.testng", "testng").versionRef("testng")

            // ------------------------------ grpc ------------------------------
            library("grpc-netty-shaded", "io.grpc", "grpc-netty-shaded").versionRef("grpc-netty-shaded")
            library("grpc-protobuf", "io.grpc", "grpc-protobuf").versionRef("grpc-protobuf")
            library("grpc-stub", "io.grpc", "grpc-stub").versionRef("grpc-stub")
            library("protobuf-java", "com.google.protobuf", "protobuf-java").versionRef("protobuf-java")
            library("javax-annotation-api", "javax.annotation", "javax.annotation-api").versionRef("javax-annotation-api")

            // ------------------------------ bundles ------------------------------
            bundle("required", listOf("slf4j-api", "org.projectlombok", "testng"))
            bundle("grpc", listOf("grpc-protobuf", "grpc-stub", "protobuf-java", "javax-annotation-api", "grpc-netty-shaded"))

        }
    }
}
