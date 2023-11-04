package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jujodevs.marvelcompose.data.entities.Event
import com.jujodevs.marvelcompose.data.repositories.EventRepository
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen

@Composable
fun EventsScreen(onClick: (Event) -> Unit) {
    var eventState by remember {
        mutableStateOf(emptyList<Event>())
    }
    LaunchedEffect(key1 = Unit) {
        eventState = EventRepository.get()
    }
    MarvelItemsListScreen(
        marvelItems = eventState,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(eventId: Int, onUpClick: () -> Unit) {
    var eventState by remember {
        mutableStateOf<Event?>(null)
    }
    LaunchedEffect(key1 = Unit) {
        eventState = EventRepository.find(eventId)
    }
    eventState?.let {
        MarvelItemDetailScreen(
            marvelItem = it,
            onUpClick = onUpClick
        )
    }
}
