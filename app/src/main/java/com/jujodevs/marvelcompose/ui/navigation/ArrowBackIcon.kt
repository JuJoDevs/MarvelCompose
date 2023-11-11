package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ArrowBackIcon(modifier: Modifier = Modifier, onBack: () -> Unit = {}) {
    IconButton(
        modifier = modifier,
        onClick = { onBack() }
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
    }
}
