plugins {
    `kotlin-dsl`
    kotlin("jvm").version("2.2.21")
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.2")
}