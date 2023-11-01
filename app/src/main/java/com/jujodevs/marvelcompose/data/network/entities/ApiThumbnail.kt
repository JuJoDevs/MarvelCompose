package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiThumbnail(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)

fun ApiThumbnail.asString() = "$path.$extension".replace("http", "https")
