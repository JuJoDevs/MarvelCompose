package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun ArrowBackIcon(onBack: () -> Unit) {
    IconButton(onClick = { onBack() }) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
    }
}
