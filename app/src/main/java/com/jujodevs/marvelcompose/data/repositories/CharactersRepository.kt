package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.network.ApiClient

object CharactersRepository : Repository<Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient.charactersService.getCharacters(
            super.offset,
            super.limit
        ).data.results.map { it.asCharacter() }
    }

    suspend fun find(id: Int): Character = super.find(
        id,
        findActionRemote = {
            ApiClient.charactersService.findCharacter(id).data.results.first().asCharacter()
        }
    )
}
