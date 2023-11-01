package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiUrl(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)
