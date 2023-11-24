package com.jujodevs.marvelcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jujodevs.marvelcompose.ui.navigation.DrawerContent
import com.jujodevs.marvelcompose.ui.navigation.Navigation
import com.jujodevs.marvelcompose.ui.theme.MarvelComposeTheme
import kotlinx.coroutines.launch

@Composable
fun MarvelApp(modifier: Modifier = Modifier) {
    val appState = rememberMarvelAppState()

    ModalNavigationDrawer(
        drawerState = appState.drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(onOptionClick = { })
            }
        },
    ) {
        Scaffold(
            topBar = appState.topBar.value,
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(
                        topBar = { appState.topBar.value = it },
                        bottomBar = { appState.bottomBar.value = it },
                        onMenuClick = {
                            appState.coroutineScope.launch { appState.drawerState.open() }
                        },
                    )
                }
            },
            bottomBar = appState.bottomBar.value,
            modifier = modifier,
        )
    }
}

@Composable
fun MarvelScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    MarvelComposeTheme(
        dynamicColor = false,
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            content()
        }
    }
}
