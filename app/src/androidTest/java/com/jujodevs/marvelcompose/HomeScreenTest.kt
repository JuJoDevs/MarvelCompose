package com.jujodevs.marvelcompose

import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.platform.app.InstrumentationRegistry
import arrow.core.Either
import com.jujodevs.marvelcompose.data.entities.Comic
import com.jujodevs.marvelcompose.ui.screens.common.MarvelItemsListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val ctx = InstrumentationRegistry.getInstrumentation().targetContext

    private val items: List<Comic> = (1..100).map {
        Comic(
            id = it,
            title = "Title $it",
            description = "Description $it",
            thumbnail = "",
            format = Comic.Format.COMIC,
            urls = emptyList(),
            references = emptyList(),
        )
    }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MarvelItemsListScreen(items = Either.Right(items), onClick = {})
        }
    }

    @Test
    fun navigatesTo51(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(50)

        onNodeWithText("Title 51").assertExists()
    }

    @Test
    fun navigatesTo51AndShowBottomSheet(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(50)

        onNode(
            hasParent(hasText("Title 51")) and
                hasContentDescription(ctx.getString(R.string.more_options)),
        ).performClick()

        onNode(
            hasAnySibling(hasText(ctx.getString(R.string.go_to_detail))) and
                hasText("Title 51"),
        ).assertExists()
    }
}
