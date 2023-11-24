package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppBarIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    contentDescription: String? = null
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier,
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}
