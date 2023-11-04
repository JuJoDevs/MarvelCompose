package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jujodevs.marvelcompose.ui.screens.CharacterDetailScreen
import com.jujodevs.marvelcompose.ui.screens.CharactersScreen
import com.jujodevs.marvelcompose.ui.screens.ComicDetailScreen
import com.jujodevs.marvelcompose.ui.screens.ComicsScreen
import com.jujodevs.marvelcompose.ui.screens.EventDetailScreen
import com.jujodevs.marvelcompose.ui.screens.EventsScreen
import com.jujodevs.marvelcompose.ui.shares.BackgroundScaffold

@Composable
fun Navigation() {
    val navController = rememberNavController()

    BackgroundScaffold()

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(
    navController: NavController,
) {
    navigation(
        startDestination = NavItem.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavItem.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(
                onClick = { character ->
                    navController.navigate(
                        NavItem.ContentDetailt(Feature.CHARACTERS).createRoute(character.id)
                    )
                }
            )
        }

        composable(NavItem.ContentDetailt(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                characterId = it.findArg(NavArg.ItemId),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavItem.ContentType(Feature.COMICS)) {
            ComicsScreen(onClick = { comic ->
                navController.navigate(
                    NavItem.ContentDetailt(Feature.COMICS).createRoute(comic.id)
                )
            })
        }

        composable(NavItem.ContentDetailt(Feature.COMICS)) {
            ComicDetailScreen(
                comicId = it.findArg(NavArg.ItemId),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavItem.ContentType(Feature.EVENTS)) {
            EventsScreen(onClick = { event ->
                navController.navigate(
                    NavItem.ContentDetailt(Feature.EVENTS).createRoute(event.id)
                )
            })
        }

        composable(NavItem.ContentDetailt(Feature.EVENTS)) {
            EventDetailScreen(
                eventId = it.findArg(NavArg.ItemId),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
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
