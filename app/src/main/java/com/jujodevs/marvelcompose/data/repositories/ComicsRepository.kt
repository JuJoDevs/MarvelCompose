package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.network.ApiClient

object ComicsRepository {
    private const val offset = 0
    private const val limit = 20
    suspend fun get(format: Comic.Format? = null): List<Comic> =
        ApiClient.comicService.getComics(
            offset,
            limit,
            format?.toStringFormat()
        ).data.results.map { it.asComic() }

    suspend fun find(id: Int): Comic =
        ApiClient.comicService.findComic(id)
            .data.results.first().asComic()
}
