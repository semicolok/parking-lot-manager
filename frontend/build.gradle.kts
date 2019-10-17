import com.moowork.gradle.node.task.NodeTask

repositories {
    jcenter()
}

plugins {
    java;
    id("org.springframework.boot") version "2.1.9.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.moowork.node") version "1.3.1"
    id("com.bmuschko.docker-spring-boot-application") version "4.3.0"
}

group = "com.manager.parkinglot"
version = "0.0.1-SNAPSHOT"

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
}


val dockerImageName: String by extra { "parking-lot-manager-frontend" }
val dockerImageTag: String by extra { "0.0.1" }

docker {
    springBootApplication {
        baseImage.set("openjdk:8-alpine")
        ports.set(listOf(3000))
        tag.set("$dockerImageName:$dockerImageTag")
    }
}

node {
    download = true
    nodeModulesDir = file("${project.projectDir}/node_modules")
}

tasks {
    val buildReactApp by registering(NodeTask::class) {
        setScript(file("node_modules/.bin/webpack"))
        setArgs(listOf(
                "--mode", "development",
                "--entry", "./src/main/webapp/javascript/Main.jsx",
                "-o", "./src/main/resources/static/dist/react-app.js"))

        dependsOn("npmInstall")
    }

    withType<ProcessResources> {
        dependsOn(buildReactApp)
    }

    withType<Delete> {
        delete("node_modules")
        delete("src/main/resources/static/dist")
    }
}
