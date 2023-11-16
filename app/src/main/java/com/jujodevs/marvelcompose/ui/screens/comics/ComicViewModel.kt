package com.jujodevs.marvelcompose.ui.screens.comics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.repositories.ComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ComicViewModel : ViewModel() {

    private val _state = MutableStateFlow(Comic.Format.values().associateWith { mutableStateOf(UiState()) })
    val state = _state.asStateFlow()

    fun formatRequested(format: Comic.Format) {
        val uiState = _state.value.getValue(format)
        if (uiState.value.comics.isRight()) return

        viewModelScope.launch {
            uiState.value = UiState(loading = true)
            uiState.value = UiState(comics = ComicsRepository.get(format))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val comics: Result<List<Comic>> = Either.Right(emptyList())
    )
}
