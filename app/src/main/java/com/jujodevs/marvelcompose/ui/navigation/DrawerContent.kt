package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    onOptionClick: (NavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS)

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.surfaceVariant,
                            MaterialTheme.colorScheme.inversePrimary,
                        ),
                    ),
                )
                .height(200.dp)
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        drawerOptions.forEach { navItem ->
            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
            ) {
                Row(
                    modifier = Modifier
                        .clickable { onOptionClick(navItem) }
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.name)
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = stringResource(id = navItem.title),
                    )
                }
            }
        }
    }
}
