package com.jujodevs.marvelcompose.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jujodevs.marvelcompose.data.entities.MarvelItem

@Composable
fun <T : MarvelItem> MarvelListItem(item: T, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            Box {
                AsyncImage(
                    model = item.thumbnail,
                    contentDescription = item.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                AppBarOverflowMenu(
                    urls = item.urls,
                    Modifier.align(Alignment.TopEnd)
                )
            }
        }
        Text(
            text = item.title,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}
