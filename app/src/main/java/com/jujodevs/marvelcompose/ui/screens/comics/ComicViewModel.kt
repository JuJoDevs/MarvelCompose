package com.jujodevs.marvelcompose.ui.screens.comics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.repositories.ComicsRepository
import kotlinx.coroutines.launch

class ComicViewModel : ViewModel() {

    val state = Comic.Format.values().associateWith { mutableStateOf(UiState()) }

    data class UiState(
        val loading: Boolean = false,
        val comics: List<Comic> = emptyList()
    )

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)
        if (uiState.value.comics.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = UiState(loading = true)
            uiState.value = UiState(comics = ComicsRepository.get(format))
        }
    }
}
