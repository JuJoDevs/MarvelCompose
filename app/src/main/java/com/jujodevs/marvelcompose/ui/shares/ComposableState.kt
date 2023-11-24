package com.jujodevs.marvelcompose.ui.shares

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberComposable(
    composable: @Composable () -> Unit = { }
) = remember {
    mutableStateOf<@Composable () -> Unit>(composable)
}
