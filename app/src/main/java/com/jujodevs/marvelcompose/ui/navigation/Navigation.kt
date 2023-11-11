package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jujodevs.marvelcompose.ui.screens.CharacterDetailScreen
import com.jujodevs.marvelcompose.ui.screens.CharactersScreen
import com.jujodevs.marvelcompose.ui.screens.ComicDetailScreen
import com.jujodevs.marvelcompose.ui.screens.ComicsScreen
import com.jujodevs.marvelcompose.ui.screens.EventDetailScreen
import com.jujodevs.marvelcompose.ui.screens.EventsScreen

@Composable
fun Navigation(
    topBar: (@Composable () -> Unit) -> Unit,
    bottomBar: (@Composable () -> Unit) -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController, topBar, bottomBar)
        comicsNav(navController, topBar, bottomBar)
        eventsNav(navController, topBar, bottomBar)
    }
}

private fun NavGraphBuilder.charactersNav(
    navController: NavHostController,
    topBar: (@Composable () -> Unit) -> Unit,
    bottomBar: (@Composable () -> Unit) -> Unit,
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                currentRoute = navController.getCurrentRoute(),
                onNavItemClick = { isCurrentRoute, item ->
                    navController.navigatePoppingUpToStartDestination(
                        isCurrentRoute = isCurrentRoute,
                        route = item.navCommand.route
                    )
                },
                onClick = { character ->
                    navController.navigate(
                        NavCommand.ContentDetailt(Feature.CHARACTERS).createRoute(character.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetailt(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                characterId = it.findArg(NavArg.ItemId),
                topBar = topBar,
                bottomBar = bottomBar,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.comicsNav(
    navController: NavHostController,
    topBar: (@Composable () -> Unit) -> Unit,
    bottomBar: (@Composable () -> Unit) -> Unit,
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                currentRoute = navController.getCurrentRoute(),
                onNavItemClick = { isCurrentRoute, item ->
                    navController.navigatePoppingUpToStartDestination(
                        isCurrentRoute = isCurrentRoute,
                        route = item.navCommand.route
                    )
                },
                onClick = { comic ->
                    navController.navigate(
                        NavCommand.ContentDetailt(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetailt(Feature.COMICS)) {
            ComicDetailScreen(
                comicId = it.findArg(NavArg.ItemId),
                topBar = topBar,
                bottomBar = bottomBar,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.eventsNav(
    navController: NavHostController,
    topBar: (@Composable () -> Unit) -> Unit,
    bottomBar: (@Composable () -> Unit) -> Unit,
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(
                topBar = topBar,
                bottomBar = bottomBar,
                currentRoute = navController.getCurrentRoute(),
                onNavItemClick = { isCurrentRoute, item ->
                    navController.navigatePoppingUpToStartDestination(
                        isCurrentRoute = isCurrentRoute,
                        route = item.navCommand.route
                    )
                },
                onClick = { event ->
                    navController.navigate(
                        NavCommand.ContentDetailt(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetailt(Feature.EVENTS)) {
            EventDetailScreen(
                eventId = it.findArg(NavArg.ItemId),
                topBar = topBar,
                bottomBar = bottomBar,
                onUpClick = { navController.popBackStack() }
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

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = when (arg.navType) {
        NavType.IntType -> arguments?.getInt(arg.key)
        else -> null
    }
    requireNotNull(value)
    return value as T
}

@Composable
private fun NavController.getCurrentRoute(): String {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route ?: ""
}
