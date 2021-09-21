package com.rdev.buildsrc

object Versions {
    const val KOTLIN = "1.5.10"
    const val COROUTINES = "1.3.3"
    const val JODA_TIME = "2.9.9.4"
    const val DAGGER = "2.19"
    const val JUNIT = "4.12"
}

object Libs {
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val KOTLIN_COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    const val JODA_TIME = "net.danlew:android.joda:${Versions.JODA_TIME}"
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    const val XML_PULL_PARSER = "xmlpull:xmlpull:1.1.3.1"

    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}