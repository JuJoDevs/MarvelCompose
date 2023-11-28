package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.network.entities.tryCall
import com.jujodevs.marvelcompose.data.network.remote.ComicService
import javax.inject.Inject

class ComicsRepository @Inject constructor(
    private val comicService: ComicService
) {
    companion object {
        private const val offset = 0
        private const val limit = 20
    }
    suspend fun get(format: Comic.Format? = null): Result<List<Comic>> = tryCall {
        comicService
            .getComics(
                offset,
                limit,
                format?.toStringFormat(),
            ).data.results.map { it.asComic() }
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
        comicService.findComic(id)
            .data.results.first().asComic()
    }
}
