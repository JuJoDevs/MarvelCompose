package com.jujodevs.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jujodevs.marvelcompose.ui.MarvelApp
import com.jujodevs.marvelcompose.ui.MarvelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                MarvelApp()
            }
        }
    }
}
