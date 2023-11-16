package com.jujodevs.marvelcompose.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jujodevs.marvelcompose.data.network.entities.Error

@Composable
fun ErrorMessage(error: Error, modifier: Modifier = Modifier) {
    val message = when (error) {
        Error.Connectivity -> "Connectivity Error"
        is Error.Server -> "Server Error: ${error.code}"
        is Error.Unknown -> "Unknown error: ${error.message}"
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = message,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
