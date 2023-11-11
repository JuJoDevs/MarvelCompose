package com.jujodevs.marvelcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jujodevs.marvelcompose.ui.navigation.Navigation

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    var topBar: @Composable () -> Unit by remember {
        mutableStateOf({})
    }
    var bottomBar: @Composable () -> Unit by remember {
        mutableStateOf({})
    }

    Scaffold(
        topBar = topBar,
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    topBar = { topBar = it },
                    bottomBar = { bottomBar = it }
                )
            }
        },
        bottomBar = bottomBar,
        modifier = modifier
    )
}

/*
{
    AppBottomNavigation(
        currentRoute = currentRoute,
        onNavItemClick = { isCurrentRoute, item ->
            navController.navigatePoppingUpToStartDestination(
                isCurrentRoute = isCurrentRoute,
                route = item.navCommand.route
            )
        }
    )
}*/
