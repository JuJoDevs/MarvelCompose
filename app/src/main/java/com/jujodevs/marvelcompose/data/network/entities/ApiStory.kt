package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiStory(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)
