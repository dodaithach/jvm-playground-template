package com.rdev.oneoff.unifyAndroidStrings

import com.rdev.utils.isEmptyDirectory
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private const val ROOT_PROJECT_DIR = "/Users/gtf/repo/go-resto"

fun main(args: Array<String>) {
    val dest = mapOf(
        "default" to "$ROOT_PROJECT_DIR/lib-pos-resources/src/main/res/values/strings.xml",
        "in" to "$ROOT_PROJECT_DIR/lib-pos-resources/src/main/res/values-in/strings.xml",
        "th" to "$ROOT_PROJECT_DIR/lib-pos-resources/src/main/res/values-th/strings.xml",
        "vi" to "$ROOT_PROJECT_DIR/lib-pos-resources/src/main/res/values-vi/strings.xml"
    )
    val legacyModules = listOf(
        "$ROOT_PROJECT_DIR/feature-pos",
        "$ROOT_PROJECT_DIR/lib-pos-activation/",
        "$ROOT_PROJECT_DIR/lib-pos-analytics/",
        "$ROOT_PROJECT_DIR/lib-pos-config/",
        "$ROOT_PROJECT_DIR/lib-pos-preference/",
        "$ROOT_PROJECT_DIR/lib-qris-activation/"
    )

    removeEmptyDirectory(legacyModules, dryRun = false)
//    merge(dest, legacyModules, dryRun = false)
}

fun merge(
    dest: Map<String, String>,
    legacyModules: List<String>,
    dryRun: Boolean = false
) {
    val legacyStringFiles = mutableListOf<String>()

    legacyModules.forEach { module ->
        File(module).walk().forEach { file ->
            val path = file.absolutePath
            if (file.isFile
                && !path.contains("/build/")
                && path.contains("/values")
                && file.absolutePath.endsWith(STRING_FILE_SUFFIX)
            ) {
                legacyStringFiles.add(file.absolutePath)
            }
        }
    }

    mergeAndroidStringFolders(legacyStringFiles, dest, dryRun = dryRun)
}

fun removeEmptyDirectory(legacyModules: List<String>, dryRun: Boolean = false) {
    legacyModules.forEach { module ->
        File(module).walk().forEach { file ->
            if (isEmptyDirectory(file)) {
                println("remove: ${file.absolutePath}")

                if (!dryRun) file.delete()
            }
        }
    }
}

private fun mergeAndroidStringFolders(
    srcPaths: List<String>,
    dest: Map<String, String>,
    dryRun: Boolean = false
) {
    srcPaths.forEach { path ->
        folderNameToLocale.forEach { (folder, locale) ->
            if (path.contains(folder)) {
                println("copy: $path")

                val srcFile = File(path)
                val reader = InputStreamReader(srcFile.inputStream())
                val destFile = File(dest[locale]!!)
                val outputStream = FileOutputStream(destFile, true)
                val writer = OutputStreamWriter(outputStream)

                copyStrings(reader, writer)

                reader.close()
                writer.close()
                outputStream.close()

                if (!dryRun) {
                    File(path).delete()
                }
            }
        }
    }
}

private fun copyStrings(src: InputStreamReader, dest: OutputStreamWriter) {
    val stringContent = src.readLines()
        .filter {
            val line = it.trim()
            line.isNotBlank() && !line.contains(META_DATA) && !line.contains(HEADER) && !line.contains(FOOTER)
        }
        .reduce { acc, s -> "$acc\n$s" }

    dest.appendLine()
    dest.append(stringContent)
}