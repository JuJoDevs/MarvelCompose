package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiComic(
    @SerializedName("characters")
    val characters: ApiReferenceList,
    @SerializedName("creators")
    val creators: ApiReferenceList,
    @SerializedName("description")
    val description: String?,
    @SerializedName("diamondCode")
    val diamondCode: String,
    @SerializedName("digitalId")
    val digitalId: Int,
    @SerializedName("ean")
    val ean: String,
    @SerializedName("events")
    val events: ApiReferenceList,
    @SerializedName("format")
    val format: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("issn")
    val issn: String,
    @SerializedName("issueNumber")
    val issueNumber: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("prices")
    val prices: List<ApiPrice>,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: ApiReferenceList,
    @SerializedName("stories")
    val stories: ApiReferenceList,
    @SerializedName("thumbnail")
    val thumbnail: ApiThumbnail,
    @SerializedName("title")
    val title: String,
    @SerializedName("upc")
    val upc: String,
    @SerializedName("urls")
    val urls: List<ApiUrl>,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("variants")
    val variants: List<ApiVariant>
)
