package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiEvent(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String
)
