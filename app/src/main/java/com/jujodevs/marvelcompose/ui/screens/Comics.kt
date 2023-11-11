package com.jujodevs.marvelcompose.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.data.repositories.ComicsRepository
import com.jujodevs.marvelcompose.ui.navigation.AppBottomNavigation
import com.jujodevs.marvelcompose.ui.navigation.NavItem
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsList
import com.jujodevs.marvelcompose.ui.screens.common.TopAppBarContentType
import com.jujodevs.marvelcompose.ui.screens.common.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ComicsScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onMenuClick: () -> Unit = {},
    currentRoute: String = "",
    onNavItemClick: (Boolean, NavItem) -> Unit = { _, _ -> },
    onClick: (Comic) -> Unit = {},
) {
    var comicState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(key1 = Unit) {
        comicState = ComicsRepository.get()
    }

    val formats = Comic.Format.values().toList()
    val pagerState = rememberPagerState { formats.size }

    topBar {
        TopAppBarContentType(title = R.string.app_name, onClick = { onMenuClick() })
    }
    Column(modifier = modifier) {
        ComicsFotmatTabRow(pagerState, formats)
        HorizontalPager(
            state = pagerState
        ) { page ->
            MarvelItemsList(
                marvelItems = comicState,
                onClick = onClick,
                modifier = Modifier
            )
        }
    }
    bottomBar {
        AppBottomNavigation(
            currentRoute = currentRoute,
            onNavItemClick = onNavItemClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ComicsFotmatTabRow(
    pagerState: PagerState,
    formats: List<Comic.Format>,
) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        formats.forEach {
            Tab(
                selected = it.ordinal == pagerState.currentPage,
                onClick = { scope.launch { pagerState.animateScrollToPage(it.ordinal) } },
                text = { Text(text = stringResource(id = it.toStringRes()).uppercase()) }
            )
        }
    }
}

@StringRes
private fun Comic.Format.toStringRes(): Int = when (this) {
    Comic.Format.COMIC -> R.string.comic
    Comic.Format.MAGAZINE -> R.string.magazine
    Comic.Format.TRADE_PAPERBACK -> R.string.trade_paperback
    Comic.Format.HARDCOVER -> R.string.hardcover
    Comic.Format.DIGEST -> R.string.digest
    Comic.Format.GRAPHIC_NOVEL -> R.string.graphic_novel
    Comic.Format.DIGITAL_COMIC -> R.string.digital_comic
    Comic.Format.INFINITE_COMIC -> R.string.infinite_comic
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
