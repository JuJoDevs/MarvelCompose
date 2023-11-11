package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.repositories.CharactersRepository
import com.jujodevs.marvelcompose.ui.navigation.AppBottomNavigation
import com.jujodevs.marvelcompose.ui.navigation.NavItem
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    currentRoute: String = "",
    onNavItemClick: (Boolean, NavItem) -> Unit = { _, _ -> },
    onClick: (Character) -> Unit = {},
) {
    var charactersState by remember { mutableStateOf(emptyList<Character>()) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }
    topBar {
        CustomTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
    }
    MarvelItemsListScreen(
        modifier = modifier,
        marvelItems = charactersState,
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
    characterId: Int,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onUpClick: () -> Unit = {},
) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(characterId)
    }

    characterState?.let {
        MarvelItemDetailScreen(
            marvelItem = it,
            topBar = topBar,
            bottomBar = bottomBar,
            onUpClick = onUpClick
        )
    }
}
