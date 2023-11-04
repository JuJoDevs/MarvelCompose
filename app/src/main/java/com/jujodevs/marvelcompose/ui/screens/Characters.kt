package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.repositories.CharactersRepository
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by remember { mutableStateOf(emptyList<Character>()) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }
    MarvelItemsListScreen(
        marvelItems = charactersState,
        onClick = onClick
    )
}

@Composable
fun CharacterDetailScreen(characterId: Int, onUpClick: () -> Unit) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(characterId)
    }
    characterState?.let {
        MarvelItemDetailScreen(
            marvelItem = it,
            onUpClick = onUpClick
        )
    }
}
