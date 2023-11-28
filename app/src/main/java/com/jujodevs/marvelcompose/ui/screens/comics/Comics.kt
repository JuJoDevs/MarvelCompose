package com.jujodevs.marvelcompose.ui.screens.comics

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.ui.navigation.AppBottomNavigation
import com.jujodevs.marvelcompose.ui.navigation.NavItem
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen
import com.jujodevs.marvelcompose.ui.screens.common.TopAppBarContentType
import com.jujodevs.marvelcompose.ui.screens.common.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComicsScreen(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onMenuClick: () -> Unit = {},
    currentRoute: String = "",
    onNavItemClick: (Boolean, NavItem) -> Unit = { _, _ -> },
    onClick: (Comic) -> Unit = {},
    viewModel: ComicViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val formats = Comic.Format.values().toList()
    val pagerState = rememberPagerState { formats.size }

    topBar {
        TopAppBarContentType(title = R.string.app_name, onClick = { onMenuClick() })
    }
    Column(modifier = modifier) {
        ComicsFotmatTabRow(pagerState, formats)
        HorizontalPager(
            state = pagerState,
        ) { page ->
            val format = formats[page]
            viewModel.formatRequested(format)
            val pageState by state.getValue(format)
            MarvelItemsListScreen(
                loading = pageState.loading,
                items = pageState.comics,
                onClick = onClick,
                modifier = Modifier,
            )
        }
    }
    bottomBar {
        AppBottomNavigation(
            currentRoute = currentRoute,
            onNavItemClick = onNavItemClick,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ComicsFotmatTabRow(
    pagerState: PagerState,
    formats: List<Comic.Format>
) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
            )
        },
    ) {
        formats.forEach {
            Tab(
                selected = it.ordinal == pagerState.currentPage,
                onClick = { scope.launch { pagerState.animateScrollToPage(it.ordinal) } },
                text = { Text(text = stringResource(id = it.toStringRes()).uppercase()) },
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
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onUpClick: () -> Unit = {},
    viewmodel: ComicDetailViewModel = hiltViewModel()
) {
    val state by viewmodel.state.collectAsState()
    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.comic,
        topBar = topBar,
        bottomBar = bottomBar,
        onUpClick = onUpClick,
        modifier = modifier,
    )
}
