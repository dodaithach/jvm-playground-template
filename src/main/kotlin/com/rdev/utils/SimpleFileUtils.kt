package com.rdev.utils

import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Files

fun isEmptyDirectory(file: File): Boolean {
    return file.isDirectory && !Files.newDirectoryStream(file.toPath()).iterator().hasNext()
}

fun openForReading(filePath: String): InputStreamReader {
    val file = openFile(filePath)
    return InputStreamReader(file.inputStream())
}

fun openForWriting(filePath: String, append: Boolean = false): OutputStreamWriter {
    val file = openFile(filePath, true)
    val outputStream = FileOutputStream(file, append)
    return OutputStreamWriter(outputStream)
}

private fun openFile(filePath: String, shouldCreate: Boolean = false): File {
    val file = File(filePath)

    if (file.exists()) {
        return file
    }

    if (!shouldCreate) {
        val errorMessage = "$filePath does not exist"
        println(errorMessage)
        throw Exception(errorMessage)
    }

    file.createNewFile()
    return file
}