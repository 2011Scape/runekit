plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("lib/ComponentResizer.jar"))
    implementation(files("lib/rs2-client-graphics.jar"))
    implementation("org.swinglabs.swingx:swingx-all:1.6.5-1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("com.runekit.MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.runekit.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}