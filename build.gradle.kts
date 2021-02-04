plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
    id("org.jetbrains.kotlin.kapt") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("io.micronaut.application") version "1.3.3"
    id("com.google.cloud.tools.jib") version "2.6.0"
}

version = "0.1"
group = "ac.leap"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
    jcenter()
}

micronaut {
    runtime("netty")
    testRuntime("kotest")
    processing {
        incremental(true)
        annotations("ac.leap.*")
    }
}

dependencies {
    kapt("io.micronaut.openapi:micronaut-openapi")
    implementation("io.micronaut:micronaut-validation")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("io.micronaut.aws:micronaut-aws-sdk-v2")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("io.micronaut:micronaut-multitenancy")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.sql:micronaut-jooq")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("org.apache.logging.log4j:log4j-core:2.12.1")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.12.1")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.12.1")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:postgresql")
}


application {
    mainClass.set("ac.leap.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("14")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }


    jib {
        to {
            image = "gcr.io/myapp/jib-image"
        }
    }
}
