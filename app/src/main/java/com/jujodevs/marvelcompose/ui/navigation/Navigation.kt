package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jujodevs.marvelcompose.ui.screens.characterdetail.CharacterDetailScreen
import com.jujodevs.marvelcompose.ui.screens.characters.CharactersScreen
import com.jujodevs.marvelcompose.ui.shares.BackgroundScaffold

@Composable
fun Navigation() {
    val navController = rememberNavController()

    BackgroundScaffold()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        charactersNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(
    navController: NavController,
) {
    composable(NavItem.Characters) {
        CharactersScreen(
            onClick = { character ->
                navController.navigate(NavItem.CharacterDetail.createRoute(character.id))
            }
        )
    }

    composable(NavItem.CharacterDetail) {
        CharacterDetailScreen(
            id = it.findArg(NavArg.ItemId),
            onUpClick = { navController.popBackStack() }
        )
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
