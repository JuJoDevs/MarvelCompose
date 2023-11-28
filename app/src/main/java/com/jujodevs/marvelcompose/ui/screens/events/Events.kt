package com.jujodevs.marvelcompose.ui.screens.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Event
import com.jujodevs.marvelcompose.ui.navigation.AppBottomNavigation
import com.jujodevs.marvelcompose.ui.navigation.NavItem
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen
import com.jujodevs.marvelcompose.ui.screens.common.TopAppBarContentType

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onMenuClick: () -> Unit = {},
    currentRoute: String = "",
    onNavItemClick: (Boolean, NavItem) -> Unit = { _, _ -> },
    onClick: (Event) -> Unit = {},
    viewModel: EventsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    topBar {
        TopAppBarContentType(title = R.string.app_name, onClick = { onMenuClick() })
    }
    MarvelItemsListScreen(
        loading = state.loading,
        items = state.events,
        onClick = onClick,
        modifier = modifier,
    )
    bottomBar {
        AppBottomNavigation(
            currentRoute = currentRoute,
            onNavItemClick = onNavItemClick,
        )
    }
}

@Composable
fun EventDetailScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onUpClick: () -> Unit = {},
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.event,
        topBar = topBar,
        bottomBar = bottomBar,
        onUpClick = onUpClick,
        modifier = modifier,
    )
}
