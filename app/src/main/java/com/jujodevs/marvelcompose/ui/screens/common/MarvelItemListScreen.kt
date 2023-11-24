package com.jujodevs.marvelcompose.ui.screens.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arrow.core.Either
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.entities.MarvelItem
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.ui.MarvelScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    items: Result<List<T>> = Either.Right(emptyList()),
    onClick: (T) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showModal by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        scope.launch {
            sheetState.hide()
            showModal = false
        }
    }

    items.fold({ ErrorMessage(it) }) { marvelItems ->
        var bottomSheetItem by remember { mutableStateOf<T?>(null) }

        MarvelItemsList(
            loading = loading,
            marvelItems = marvelItems,
            onClick = onClick,
            onItemMore = {
                bottomSheetItem = it
                showModal = true
                scope.launch { sheetState.show() }
            },
            modifier = modifier,
        )

        if (showModal) {
            ModalBottomSheet(
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                        showModal = false
                    }
                },
                sheetState = sheetState,
            ) {
                MarvelItemBottomPreview(
                    item = bottomSheetItem,
                    onGoToDetail = onClick,
                )
            }
        }
    }
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    loading: Boolean,
    marvelItems: List<T>,
    onClick: (T) -> Unit,
    onItemMore: (T) -> Unit,
    modifier: Modifier = Modifier
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
                        onItemMore = onItemMore,
                        modifier = Modifier.clickable {
                            onClick(it)
                        },
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
