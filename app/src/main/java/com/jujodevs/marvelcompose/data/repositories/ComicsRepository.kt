package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.network.ApiClient

object ComicsRepository : Repository<Comic>() {

    suspend fun get(format: Comic.Format? = null): List<Comic> = super.get {
        ApiClient.comicService.getComics(
            super.offset,
            super.limit,
            format?.toStringFormat()
        ).data.results.map { it.asComic() }
    }

    suspend fun find(id: Int): Comic = super.find(
        id = id,
        findActionRemote = {
            ApiClient.comicService.findComic(id)
                .data.results.first().asComic()
        }
    )
}
