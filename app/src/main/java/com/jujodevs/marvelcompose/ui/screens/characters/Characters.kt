package com.jujodevs.marvelcompose.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.ui.navigation.AppBottomNavigation
import com.jujodevs.marvelcompose.ui.navigation.NavItem
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen
import com.jujodevs.marvelcompose.ui.screens.common.TopAppBarContentType

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onMenuClick: () -> Unit = {},
    currentRoute: String = "",
    onNavItemClick: (Boolean, NavItem) -> Unit = { _, _ -> },
    onClick: (Character) -> Unit = {},
    viewModel: CharacterViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()

    topBar {
        TopAppBarContentType(title = R.string.app_name, onClick = { onMenuClick() })
    }
    MarvelItemsListScreen(
        modifier = modifier,
        loading = state.loading,
        marvelItems = state.characters,
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
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    viewmodel: CharacterDetailViewmodel = viewModel(),
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onUpClick: () -> Unit = {},

) {
    val state by viewmodel.state.collectAsState()

    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.character,
        topBar = topBar,
        bottomBar = bottomBar,
        onUpClick = onUpClick,
        modifier = modifier
    )
}
