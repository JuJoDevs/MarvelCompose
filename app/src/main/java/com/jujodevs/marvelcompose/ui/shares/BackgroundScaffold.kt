package com.jujodevs.marvelcompose.ui.shares

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundScaffold(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = {})
        },
        content = { paddingValues ->
            Spacer(modifier = Modifier.padding(paddingValues))
        },
        modifier = modifier
    )
}
