package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiSeries(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ApiSerie>,
    @SerializedName("returned")
    val returned: Int
)
