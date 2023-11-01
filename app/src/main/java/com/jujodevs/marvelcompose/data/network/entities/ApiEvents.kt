package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiEvents(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ApiEvent>,
    @SerializedName("returned")
    val returned: Int
)
