package com.jujodevs.marvelcompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jujodevs.marvelcompose.ui.screens.MainScaffold
import com.jujodevs.marvelcompose.ui.theme.MarvelComposeTheme

@Composable
fun MarvelApp() {
    MarvelScreen {
        MainScaffold()
    }
}

@Composable
fun MarvelScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    MarvelComposeTheme(
        dynamicColor = false
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
