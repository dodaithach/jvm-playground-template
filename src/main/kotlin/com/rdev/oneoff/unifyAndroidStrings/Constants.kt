package com.rdev.oneoff.unifyAndroidStrings

internal const val META_DATA = "<?xml"
internal const val HEADER = "<resources>"
internal const val FOOTER = "</resources>"
internal const val STRING_FILE_SUFFIX = "strings.xml"

internal val folderNameToLocale = mutableMapOf(
    "/values/" to "default",
    "/values-in/" to "in",
    "/values-th/" to "th",
    "/values-vi/" to "vi"
)