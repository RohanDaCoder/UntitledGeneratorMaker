plugins {
    kotlin("jvm") version "2.3.0-RC3"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "me.rohandacoder"
version = "1.2-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://repo.panda-lang.org/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.10-R0.1-SNAPSHOT")
    implementation("dev.rollczi:litecommands-bukkit:3.10.6")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.jeff-media:custom-block-data:2.2.4")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.shadowJar {
    archiveFileName.set("untitled-generator-maker-$version.jar")

    if (System.getenv("build_environment") == "ci") {
        // CI/CD environment - output to build/libs/ for standard artifact collection
        print("CI/CD environment detected!\n")
        destinationDirectory.set(layout.buildDirectory.dir("libs"))
    } else {
        // Local development - output to your Minecraft server directory
        print("Local environment detected!\n")
        destinationDirectory.set(file("C:/Users/User/Documents/MC SERVER/plugins"))
    }
}
tasks.compileJava {
    options.compilerArgs.add("-parameters")
}