package com.jujodevs.marvelcompose.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterViewModel @Inject constructor(
    charactersRepository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, characters = charactersRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: Result<List<Character>> = Either.Right(emptyList())
    )
}
