package com.example.squadmaker.model.repository.utils

import android.net.Uri

fun String.concat(thumbnailExtension: String): String {
    return Uri.parse(
        this
            .plus(".")
            .plus(thumbnailExtension)
    ).toString()
}