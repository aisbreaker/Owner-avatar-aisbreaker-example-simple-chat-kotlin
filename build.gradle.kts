plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.aisbreaker.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.aisbreaker:aisbreaker-api-java:0.1.2")
}

application {
    mainClass.set("org.aisbreaker.example.AIsBreakerSimpleChatKt")
}
