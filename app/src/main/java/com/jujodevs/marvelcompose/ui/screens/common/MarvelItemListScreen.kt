package com.jujodevs.marvelcompose.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.entities.MarvelItem
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar
import com.jujodevs.marvelcompose.ui.theme.MarvelComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    modifier: Modifier = Modifier,
    marvelItems: List<T> = emptyList(),
    onClick: (T) -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
        content = { paddingValues ->
            MarvelItemsList(
                marvelItems = marvelItems,
                onClick = onClick,
                modifier = Modifier.padding(paddingValues)
            )
        },
        modifier = modifier
    )
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    marvelItems: List<T>,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
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

@Preview
@Composable
private fun CharactersScreenPreview() {
    MarvelComposeTheme {
        MarvelItemsListScreen<Character> {}
    }
}
