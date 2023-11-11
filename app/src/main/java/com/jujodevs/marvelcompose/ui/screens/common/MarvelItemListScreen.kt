package com.jujodevs.marvelcompose.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.entities.MarvelItem
import com.jujodevs.marvelcompose.ui.MarvelScreen

@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    marvelItems: List<T> = emptyList(),
    onClick: (T) -> Unit,
) {
    MarvelItemsList(
        loading = loading,
        marvelItems = marvelItems,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    loading: Boolean,
    marvelItems: List<T>,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        if (loading) {
            CircularProgressIndicator()
        }

        if (marvelItems.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
            ) {
                items(marvelItems) {
                    MarvelListItem(
                        item = it,
                        modifier = Modifier.clickable {
                            onClick(it)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharactersScreenPreview() {
    MarvelScreen {
        MarvelItemsListScreen<Character>(loading = false) {}
    }
}
