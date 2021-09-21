import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.rdev.buildsrc.Libs

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Libs.KOTLIN_STDLIB)
    implementation(Libs.KOTLIN_COROUTINES)
    implementation(Libs.XML_PULL_PARSER)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}