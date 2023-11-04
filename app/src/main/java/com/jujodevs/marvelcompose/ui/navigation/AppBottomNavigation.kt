package com.jujodevs.marvelcompose.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigation(
    currentRoute: String,
    onNavItemClick: (Boolean, NavItem) -> Unit,
) {
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        NavItem.values().forEach { item ->
            val title = stringResource(id = item.title)
            val isCurrentRoute = currentRoute.contains(item.navCommand.feature.route)
            NavigationBarItem(
                selected = isCurrentRoute,
                onClick = { onNavItemClick(isCurrentRoute, item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = title
                    )
                },
                label = { Text(text = title) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.inversePrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}
