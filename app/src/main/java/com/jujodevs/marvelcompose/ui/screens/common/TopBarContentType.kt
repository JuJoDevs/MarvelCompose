package com.jujodevs.marvelcompose.ui.screens.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jujodevs.marvelcompose.ui.navigation.AppBarIcon
import com.jujodevs.marvelcompose.ui.shares.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarContentType(
    @StringRes title: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomTopAppBar(
        title = { Text(text = stringResource(id = title)) },
        navigationIcon = {
            AppBarIcon(imageVector = Icons.Default.Menu, onClick = { onClick() })
        },
        modifier = modifier
    )
}
