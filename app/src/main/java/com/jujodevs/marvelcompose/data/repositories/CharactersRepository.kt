package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.entities.Reference
import com.jujodevs.marvelcompose.data.entities.Url
import com.jujodevs.marvelcompose.data.network.ApiClient
import com.jujodevs.marvelcompose.data.network.entities.ApiCharacter
import com.jujodevs.marvelcompose.data.network.entities.ApiUrl
import com.jujodevs.marvelcompose.data.network.entities.asString

object CharactersRepository {

    private const val OFFSET = 0
    private const val LIMIT = 100

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(OFFSET, LIMIT)

        return result.data.results.map {
            it.asCharacter()
        }
    }

    suspend fun findCharacter(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId)
        return result.data.results.first().asCharacter()
    }
}

fun ApiCharacter.asCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }

    return Character(
        id,
        name,
        description,
        thumbnail.asString(),
        comics,
        events,
        stories,
        series,
        urls.map { it.asUrl() }
    )
}

fun ApiUrl.asUrl(): Url = Url(type, url)
