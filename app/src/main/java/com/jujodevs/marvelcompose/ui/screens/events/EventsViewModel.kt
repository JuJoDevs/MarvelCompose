package com.jujodevs.marvelcompose.ui.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.jujodevs.marvelcompose.data.entities.Event
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EventsViewModel @Inject constructor(
    eventRepository: EventRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, events = eventRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val events: Result<List<Event>> = Either.Right(emptyList())
    )
}
