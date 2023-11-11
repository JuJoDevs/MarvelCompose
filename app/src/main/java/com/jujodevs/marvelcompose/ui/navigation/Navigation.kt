package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jujodevs.marvelcompose.ui.screens.characters.CharacterDetailScreen
import com.jujodevs.marvelcompose.ui.screens.characters.CharactersScreen
import com.jujodevs.marvelcompose.ui.screens.comics.ComicDetailScreen
import com.jujodevs.marvelcompose.ui.screens.comics.ComicsScreen
import com.jujodevs.marvelcompose.ui.screens.events.EventDetailScreen
import com.jujodevs.marvelcompose.ui.screens.events.EventsScreen

@Composable
fun Navigation(
    topBar: (@Composable () -> Unit) -> Unit,
    bottomBar: (@Composable () -> Unit) -> Unit,
    onMenuClick: () -> Unit,
) {
    val navigationState = rememberNavigationState()

    NavHost(
        navController = navigationState.navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navigationState, topBar, bottomBar, onMenuClick)
        comicsNav(navigationState, topBar, bottomBar, onMenuClick)
        eventsNav(navigationState, topBar, bottomBar, onMenuClick)
    }
}

private fun NavGraphBuilder.charactersNav(
    navigationState: NavigationState,
    topBar: (@Composable () -> Unit) -> Unit = {},
    bottomBar: (@Composable () -> Unit) -> Unit = {},
    onMenuClick: () -> Unit = {},
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                onMenuClick = onMenuClick,
                currentRoute = navigationState.currentRoute,
                onNavItemClick = { isCurrentRoute, item ->
                    navigationState.navigatePoppingUpToStartDestination(
                        isCurrentRoute = isCurrentRoute,
                        route = item.navCommand.route
                    )
                },
                onClick = { character ->
                    navigationState.navigate(
                        NavCommand.ContentDetail(Feature.CHARACTERS).createRoute(character.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetail(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                onUpClick = { navigationState.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.comicsNav(
    navigationState: NavigationState,
    topBar: (@Composable () -> Unit) -> Unit,
    bottomBar: (@Composable () -> Unit) -> Unit,
    onMenuClick: () -> Unit = {},
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                onMenuClick = onMenuClick,
                currentRoute = navigationState.currentRoute,
                onNavItemClick = { isCurrentRoute, item ->
                    navigationState.navigatePoppingUpToStartDestination(
                        isCurrentRoute = isCurrentRoute,
                        route = item.navCommand.route
                    )
                },
                onClick = { comic ->
                    navigationState.navigate(
                        NavCommand.ContentDetail(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetail(Feature.COMICS)) {
            ComicDetailScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                onUpClick = { navigationState.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.eventsNav(
    navigationState: NavigationState,
    topBar: (@Composable () -> Unit) -> Unit,
    bottomBar: (@Composable () -> Unit) -> Unit,
    onMenuClick: () -> Unit = {},
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                currentRoute = navigationState.currentRoute,
                onMenuClick = onMenuClick,
                onNavItemClick = { isCurrentRoute, item ->
                    navigationState.navigatePoppingUpToStartDestination(
                        isCurrentRoute = isCurrentRoute,
                        route = item.navCommand.route
                    )
                },
                onClick = { event ->
                    navigationState.navigate(
                        NavCommand.ContentDetail(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetail(Feature.EVENTS)) {
            EventDetailScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                onUpClick = { navigationState.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}
