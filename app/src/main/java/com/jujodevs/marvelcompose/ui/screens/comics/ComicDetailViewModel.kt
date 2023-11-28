package com.jujodevs.marvelcompose.ui.screens.comics

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.repositories.ComicsRepository
import com.jujodevs.marvelcompose.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    comicsRepository: ComicsRepository
) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(comic = comicsRepository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val comic: Result<Comic?> = Either.Right(null)
    )
}
