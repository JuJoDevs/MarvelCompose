package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.MarvelItem
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.network.entities.tryCall

abstract class Repository<T : MarvelItem> {

    private var cache: List<T> = emptyList()
    internal val offset = 0
    internal val limit = 100

    internal suspend fun get(getAction: suspend () -> List<T>): Result<List<T>> =
        tryCall {
            if (cache.isEmpty()) {
                cache = getAction()
            }
            cache
        }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): Result<T> = tryCall { cache.find { it.id == id } ?: findActionRemote() }
}
