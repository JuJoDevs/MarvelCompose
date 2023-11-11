package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNavigationState(
    navigationState: NavHostController = rememberNavController()
) = NavigationState(navigationState)

class NavigationState(val navController: NavHostController) {
    val currentRoute
        @Composable get() = navController.getCurrentRoute()

    fun navigatePoppingUpToStartDestination(isCurrentRoute: Boolean, route: String) {
        navController.navigatePoppingUpToStartDestination(isCurrentRoute, route)
    }

    fun navigate(route: String) {
        navController.navigate(route)
    }

    fun popBackStack() = navController.popBackStack()
}

@Composable
private fun NavHostController.getCurrentRoute(): String {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route ?: ""
}
