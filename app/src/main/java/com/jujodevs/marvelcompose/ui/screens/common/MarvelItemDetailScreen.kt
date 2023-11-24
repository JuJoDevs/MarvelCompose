package com.jujodevs.marvelcompose.ui.screens.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import arrow.core.right
import coil.compose.AsyncImage
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.entities.MarvelItem
import com.jujodevs.marvelcompose.data.entities.Reference
import com.jujodevs.marvelcompose.data.entities.ReferenceList
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.ui.navigation.AppBarIcon
import com.jujodevs.marvelcompose.ui.navigation.ArrowBackIcon
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar
import com.jujodevs.marvelcompose.ui.theme.MarvelComposeTheme

@Composable
fun MarvelItemDetailScreen(
    marvelItem: Result<MarvelItem?>,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onUpClick: () -> Unit = {}
) {
    val context = LocalContext.current

    if (loading) {
        CircularProgressIndicator()
    }

    marvelItem.fold({ ErrorMessage(it) }) { item ->
        topBar { (DetailTopbar(item, onUpClick)) }

        if (item != null) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                item {
                    Header(item)
                }
                item.references.forEach {
                    val (icon, @StringRes stringRes) = it.type.createUiData()
                    section(icon, stringRes, it.references)
                }
            }
        }

        bottomBar { DetailBottomBar(item, context) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopbar(
    marvelItem: MarvelItem?,
    onUpClick: () -> Unit
) {
    CustomTopAppBar(
        title = { Text(text = marvelItem?.title ?: "") },
        navigationIcon = {
            ArrowBackIcon(onBack = onUpClick)
        },
        actions = {
            AppBarOverflowMenu(marvelItem?.urls ?: emptyList())
        },
    )
}

@Composable
fun Header(item: MarvelItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        AsyncImage(
            model = item.thumbnail,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp, 0.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun LazyListScope.section(icon: ImageVector, @StringRes name: Int, items: List<Reference>) {
    if (items.isEmpty()) return
    item {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
        )
    }
    items(items) {
        ListItem(
            leadingContent = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            },
            headlineContent = { Text(it.name) },
        )
    }
}

private fun ReferenceList.Type.createUiData(): Pair<ImageVector, Int> = when (this) {
    ReferenceList.Type.CHARACTER -> Icons.Default.Person to R.string.characters
    ReferenceList.Type.COMIC -> Icons.Default.Book to R.string.comics
    ReferenceList.Type.STORY -> Icons.Default.Bookmark to R.string.stories
    ReferenceList.Type.EVENT -> Icons.Default.Event to R.string.events
    ReferenceList.Type.SERIES -> Icons.Default.Collections to R.string.series
}

@Composable
private fun DetailBottomBar(
    marvelItem: MarvelItem?,
    context: Context
) {
    BottomAppBar(
        actions = {
            AppBarIcon(
                imageVector = Icons.Default.Menu,
                onClick = { /*TODO*/ },
            )
            AppBarIcon(
                imageVector = Icons.Default.Favorite,
                onClick = { /*TODO*/ },
            )
        },
        floatingActionButton = {
            if (marvelItem?.urls?.isNotEmpty() == true) {
                FloatingActionButton(onClick = { shareCharacter(context, marvelItem) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.share_character),
                    )
                }
            }
        },
    )
}

fun shareCharacter(context: Context, item: MarvelItem?) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(item?.title)
        .setText(item?.urls?.first()?.url)
        .intent
        .also(context::startActivity)
}

@Preview
@Composable
private fun CharacterDetailScreenPreview() {
    MarvelComposeTheme {
        MarvelItemDetailScreen(
            loading = false,
            marvelItem = Character(
                1,
                "preview",
                "description preview",
                "",
                emptyList(),
                emptyList(),
            ).right(),
        )
    }
}
