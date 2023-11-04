package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiEvent(
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: ApiThumbnail,
    @SerializedName("characters")
    val characters: ApiReferenceList,
    @SerializedName("comics")
    val comics: ApiReferenceList,
    @SerializedName("series")
    val series: ApiReferenceList,
    @SerializedName("stories")
    val stories: ApiReferenceList,
    @SerializedName("urls")
    val urls: List<ApiUrl>,
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String,
    @SerializedName("previous")
    val previous: ApiReference,
    @SerializedName("next")
    val next: ApiReference,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)
