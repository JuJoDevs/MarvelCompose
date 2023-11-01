package com.jujodevs.marvelcompose.data.network.entities

import com.google.gson.annotations.SerializedName

data class ApiCharacter(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("thumbnail")
    val thumbnail: ApiThumbnail,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("comics")
    val comics: ApiComics,
    @SerializedName("series")
    val series: ApiSeries,
    @SerializedName("stories")
    val stories: ApiStories,
    @SerializedName("events")
    val events: ApiEvents,
    @SerializedName("urls")
    val urls: List<ApiUrl>
)
