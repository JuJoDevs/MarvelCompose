package com.jujodevs.marvelcompose.ui.screens.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Url

@Composable
fun AppBarOverflowMenu(urls: List<Url>, modifier: Modifier = Modifier) {
    if (urls.isEmpty()) return

    var showMenu by remember {
        mutableStateOf(false)
    }
    val uriHandler = LocalUriHandler.current

    IconButton(
        onClick = { showMenu = !showMenu },
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Actions",
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
            offset = DpOffset((dimensionResource(id = R.dimen.offSetDropdownMenu)), 0.dp),
        ) {
            urls.forEach { url ->
                DropdownMenuItem(
                    text = {
                        ListItem(
                            headlineContent = {
                                Text(text = url.type)
                            },
                        )
                    },
                    onClick = {
                        uriHandler.openUri(url.url)
                        showMenu = false
                    },
                )
            }
        }
    }
}
