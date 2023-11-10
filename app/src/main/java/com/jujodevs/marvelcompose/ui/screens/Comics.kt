package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.repositories.ComicsRepository
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsList
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ComicsScreen(modifier: Modifier = Modifier, onClick: (Comic) -> Unit) {
    var comicState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(key1 = Unit) {
        comicState = ComicsRepository.get()
    }

    val formats = Comic.Format.values()

    Scaffold(
        topBar = {
            CustomTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
        content = { paddingValues ->

            val pageState = rememberPagerState { formats.size }

            HorizontalPager(
                state = pageState,
                contentPadding = paddingValues
            ) { page ->
                MarvelItemsList(
                    marvelItems = comicState,
                    onClick = onClick,
                    modifier = Modifier.padding()
                )
            }
        },
        modifier = modifier
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
