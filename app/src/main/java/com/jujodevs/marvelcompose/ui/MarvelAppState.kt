package com.jujodevs.marvelcompose.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.jujodevs.marvelcompose.ui.shares.rememberComposable
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMarvelAppState(
    topBar: MutableState<@Composable () -> Unit> = rememberComposable(),
    bottomBar: MutableState<@Composable () -> Unit> = rememberComposable(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MarvelAppState = remember(topBar, bottomBar, drawerState, coroutineScope) {
    MarvelAppState(topBar, bottomBar, drawerState, coroutineScope)
}

class MarvelAppState(
    var topBar: MutableState<@Composable () -> Unit>,
    val bottomBar: MutableState<@Composable () -> Unit>,
    val drawerState: DrawerState,
    val coroutineScope: CoroutineScope,
)
