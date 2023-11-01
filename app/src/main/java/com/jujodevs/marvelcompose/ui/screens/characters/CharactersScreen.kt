package com.jujodevs.marvelcompose.ui.screens.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.repositories.CharactersRepository
import com.jujodevs.marvelcompose.ui.screens.characterdetail.AppBarOverflowMenu
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar
import com.jujodevs.marvelcompose.ui.theme.MarvelComposeTheme

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    onClick: (Character) -> Unit
) {
    var charactersState by rememberSaveable {
        mutableStateOf(emptyList<Character>())
    }

    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.getCharacters()
    }

    CharactersScreen(
        modifier = modifier,
        characters = charactersState,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    characters: List<Character> = emptyList(),
    onClick: (Character) -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = modifier.padding(paddingValues)
            ) {
                items(characters) {
                    CharacterItem(
                        character = it,
                        modifier = modifier.clickable {
                            onClick(it)
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            Box {
                AsyncImage(
                    model = character.thumbnail,
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                AppBarOverflowMenu(
                    urls = character.urls,
                    Modifier.align(Alignment.TopEnd)
                )
            }
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}

@Suppress("MagicNumber")
@Preview
@Composable
private fun CharactersScreenPreview() {
    MarvelComposeTheme {
        CharactersScreen {}
    }
}
