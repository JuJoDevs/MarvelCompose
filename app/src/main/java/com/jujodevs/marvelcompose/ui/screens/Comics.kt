package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.jujodevs.marvelcompose.ui.navigation.AppBottomNavigation
import com.jujodevs.marvelcompose.ui.navigation.NavItem
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsList
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ComicsScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    currentRoute: String = "",
    onNavItemClick: (Boolean, NavItem) -> Unit = { _, _ -> },
    onClick: (Comic) -> Unit = {},
) {
    var comicState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(key1 = Unit) {
        comicState = ComicsRepository.get()
    }

    val formats = Comic.Format.values()
    val pageState = rememberPagerState { formats.size }

    topBar {
        CustomTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
    }
    HorizontalPager(
        state = pageState,
        modifier = modifier
    ) { page ->
        MarvelItemsList(
            marvelItems = comicState,
            onClick = onClick,
            modifier = Modifier.padding()
        )
    }
    bottomBar {
        AppBottomNavigation(
            currentRoute = currentRoute,
            onNavItemClick = onNavItemClick
        )
    }
}

@Composable
fun ComicDetailScreen(
    comicId: Int,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onUpClick: () -> Unit,
) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(key1 = Unit) {
        comicState = ComicsRepository.find(comicId)
    }
    comicState?.let {
        MarvelItemDetailScreen(
            marvelItem = it,
            topBar = topBar,
            bottomBar = bottomBar,
            onUpClick = onUpClick
        )
    }
}
