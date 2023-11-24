package com.jujodevs.marvelcompose.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jujodevs.marvelcompose.R
import com.jujodevs.marvelcompose.data.entities.Character
import com.jujodevs.marvelcompose.data.entities.MarvelItem
import com.jujodevs.marvelcompose.ui.MarvelScreen

@Suppress("MagicNumber")
@Composable
fun <T : MarvelItem> MarvelItemBottomPreview(
    item: T?,
    modifier: Modifier = Modifier,
    onGoToDetail: (T) -> Unit
) {
    if (item != null) {
        Row(
            modifier = modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                model = item.thumbnail,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(1 / 1.5f),
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(text = item.description)
                Button(
                    onClick = { onGoToDetail(item) },
                    modifier = Modifier.align(Alignment.End),
                ) {
                    Text(text = stringResource(id = R.string.go_to_detail))
                }
            }
        }
    }
}

@Preview
@Composable
private fun MarvelItemBottomPreviewPrev() {
    MarvelScreen {
        MarvelItemBottomPreview(
            item = Character(
                1,
                "Character",
                "asdoasojdn asidasojdnoasndoafb sdifnaodfnoasnmdflkadnofnadlf " +
                    "asndojagfonsldnfojapsfnjikosenfglsdnfljnsdlgjnsoldgnlskdmfpoansofnasodngfol" +
                    "sngonsolgnsoefoipsenm",
                "",
                emptyList(),
                emptyList(),
            ),
        ) {}
    }
}
