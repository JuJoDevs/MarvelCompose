package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.repositories.ComicsRepository
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen

@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(key1 = Unit) {
        comicState = ComicsRepository.get()
    }
    MarvelItemsListScreen(
        marvelItems = comicState,
        onClick = onClick
    )
}

@Composable
fun ComicDetailScreen(comicId: Int, onUpClick: () -> Unit) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(key1 = Unit) {
        comicState = ComicsRepository.find(comicId)
    }
    comicState?.let {
        MarvelItemDetailScreen(
            marvelItem = it,
            onUpClick = onUpClick
        )
    }
}
