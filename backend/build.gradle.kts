repositories {
    jcenter()
}

plugins {
    java;
    id("org.springframework.boot") version "2.1.9.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("net.ltgt.apt") version "0.21"
    id("net.ltgt.apt-idea") version "0.21"
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
    id("com.bmuschko.docker-spring-boot-application") version "4.3.0"
}

group = "com.manager.parkinglot"
version = "0.0.1-SNAPSHOT"

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

querydsl {
    querydslSourcesDir = "src/querydsl/java"
    jpa = true
    querydslDefault = true
}

sourceSets.getByName("main").java.srcDir("src/querydsl/java")

sourceSets.getByName("test").java.srcDir("src/integrationtest/java")
sourceSets.getByName("test").resources.srcDir("src/integrationtest/resources")

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.3.1.Final")

    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-starter-batch")
    compile("org.springframework.boot:spring-boot-starter-actuator")

    compile("io.springfox:springfox-swagger2:2.9.2")
    compile("io.springfox:springfox-swagger-ui:2.9.2")

    compile("org.apache.commons:commons-lang3")
    compile("com.google.guava:guava:28.1-jre")
    compile("org.mapstruct:mapstruct:1.3.1.Final")
    compile("org.projectlombok:lombok")

    compile("com.squareup.okhttp3:logging-interceptor:3.12.0")
    compile("com.squareup.retrofit2:retrofit:2.6.2")
    compile("com.squareup.retrofit2:converter-jackson:2.6.2")

    compile("com.querydsl:querydsl-jpa")
    compile("com.querydsl:querydsl-apt")

    runtime("com.h2database:h2")
    runtime("mysql:mysql-connector-java")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-inline")
    testImplementation("org.mockito:mockito-junit-jupiter:2.23.4")

    testRuntime("org.junit.jupiter:junit-jupiter-engine")
}

val dockerImageName: String by extra { "parking-lot-manager" }
val dockerImageTag: String by extra { "0.0.1" }

docker {
    springBootApplication {
        baseImage.set("openjdk:8-alpine")
        ports.set(listOf(8080))
        tag.set("$dockerImageName:$dockerImageTag")
    }
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}
