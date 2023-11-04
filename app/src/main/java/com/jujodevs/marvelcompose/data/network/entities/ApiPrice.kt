package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiPrice(
    @SerializedName("price")
    val price: Double,
    @SerializedName("type")
    val type: String
)
