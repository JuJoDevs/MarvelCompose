package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.MarvelItem

abstract class Repository<T : MarvelItem> {

    private var cache: List<T> = emptyList()
    internal val offset = 0
    internal val limit = 100

    internal suspend fun get(getAction: suspend () -> List<T>): List<T> {
        if (cache.isEmpty()) {
            cache = getAction()
        }
        return cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): T {
        val item = cache.find { it.id == id }
        if (item != null) return item

        return findActionRemote()
    }
}
