package com.jujodevs.marvelcompose.ui.screens.common

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.MarvelItem
import com.jujodevs.marvelcompose.ui.navigation.AppBarIcon
import com.jujodevs.marvelcompose.ui.navigation.ArrowBackIcon
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelItemDetailScaffold(
    item: MarvelItem,
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = { Text(text = item.title) },
                navigationIcon = {
                    ArrowBackIcon(onUpClick)
                },
                actions = {
                    AppBarOverflowMenu(item.urls)
                }
            )
        },
        content = content,
        bottomBar = {
            BottomAppBar(
                actions = {
                    AppBarIcon(
                        imageVector = Icons.Default.Menu,
                        onClick = { /*TODO*/ }
                    )
                    AppBarIcon(
                        imageVector = Icons.Default.Favorite,
                        onClick = { /*TODO*/ }
                    )
                },
                floatingActionButton = {
                    if (item.urls.isNotEmpty()) {
                        FloatingActionButton(onClick = { shareCharacter(context, item) }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(R.string.share_character)
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
    )
}

fun shareCharacter(context: Context, item: MarvelItem) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(item.title)
        .setText(item.urls.first().url)
        .intent
        .also(context::startActivity)
}
