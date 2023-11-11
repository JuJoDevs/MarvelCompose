package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Event
import com.jujodevs.marvelcompose.data.repositories.EventRepository
import com.jujodevs.marvelcompose.ui.navigation.AppBottomNavigation
import com.jujodevs.marvelcompose.ui.navigation.NavItem
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen
import com.jujodevs.marvelcompose.ui.screens.common.TopAppBarContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onMenuClick: () -> Unit = {},
    currentRoute: String = "",
    onNavItemClick: (Boolean, NavItem) -> Unit = { _, _ -> },
    onClick: (Event) -> Unit = {},
) {
    var eventState by remember {
        mutableStateOf(emptyList<Event>())
    }
    LaunchedEffect(key1 = Unit) {
        eventState = EventRepository.get()
    }

    topBar {
        TopAppBarContentType(title = R.string.app_name, onClick = { onMenuClick() })
    }
    MarvelItemsListScreen(
        modifier = modifier,
        marvelItems = eventState,
        onClick = onClick
    )
    bottomBar {
        AppBottomNavigation(
            currentRoute = currentRoute,
            onNavItemClick = onNavItemClick
        )
    }
}

@Composable
fun EventDetailScreen(
    eventId: Int,
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onUpClick: () -> Unit = {},
) {
    var eventState by remember {
        mutableStateOf<Event?>(null)
    }
    LaunchedEffect(key1 = Unit) {
        eventState = EventRepository.find(eventId)
    }
    eventState?.let {
        MarvelItemDetailScreen(
            marvelItem = it,
            modifier = modifier,
            topBar = topBar,
            bottomBar = bottomBar,
            onUpClick = onUpClick
        )
    }
}
