package com.jujodevs.marvelcompose.ui.screens.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.entities.Reference
import com.jujodevs.marvelcompose.data.repositories.CharactersRepository
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar
import com.jujodevs.marvelcompose.ui.theme.MarvelComposeTheme

@Composable
fun CharacterDetailScreen(
    id: Int,
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = {},
) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.findCharacter(id)
    }

    characterState?.let { c ->
        CharacterDetailScreen(
            character = c,
            modifier = modifier,
            onUpClick = onUpClick
        )
    } ?: Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(52.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: Character,
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = { Text(text = character.name) },
                navigationIcon = {
                    ArrowBackIcon(onUpClick)
                },
                actions = {
                    AppBarOverflowMenu(character.urls)
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                item {
                    Header(character)
                }
                section(Icons.Default.Collections, "Series", character.series)
                section(Icons.Default.Event, "Events", character.events)
                section(Icons.Default.Book, "Comics", character.comics)
                section(Icons.Default.Bookmark, "Stories", character.stories)
            }
        },
        modifier = modifier
    )
}

@Composable
private fun ArrowBackIcon(onBack: () -> Unit) {
    IconButton(onClick = { onBack() }) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
    }
}

fun LazyListScope.section(icon: ImageVector, name: String, items: List<Reference>) {
    if (items.isEmpty()) return
    item {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            leadingContent = {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            },
            headlineContent = { Text(it.name) }
        )
    }
}

@Composable
fun Header(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = character.thumbnail,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun CharacterDetailScreenPreview() {
    MarvelComposeTheme {
        CharacterDetailScreen(id = 1009368)
    }
}
