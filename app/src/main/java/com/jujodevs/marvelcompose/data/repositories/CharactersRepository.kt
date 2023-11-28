package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.network.remote.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val service: CharactersService
) : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        service.getCharacters(
            super.offset,
            super.limit,
        ).data.results.map { it.asCharacter() }
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
            service.findCharacter(id).data.results.first().asCharacter()
        },
    )
}
