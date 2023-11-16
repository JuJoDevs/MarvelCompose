package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.network.ApiClient
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.network.entities.tryCall

object ComicsRepository {
    private const val offset = 0
    private const val limit = 20
    suspend fun get(format: Comic.Format? = null): Result<List<Comic>> = tryCall {
        ApiClient
            .comicService
            .getComics(
                offset,
                limit,
                format?.toStringFormat()
            ).data.results.map { it.asComic() }
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
        ApiClient.comicService.findComic(id)
            .data.results.first().asComic()
    }
}
