package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.network.ApiClient
import com.jujodevs.marvelcompose.data.network.entities.Result

object CharactersRepository : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        ApiClient.charactersService.getCharacters(
            super.offset,
            super.limit,
        ).data.results.map { it.asCharacter() }
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
            ApiClient.charactersService.findCharacter(id).data.results.first().asCharacter()
        },
    )
}
