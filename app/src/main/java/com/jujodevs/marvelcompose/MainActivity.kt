package com.jujodevs.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jujodevs.marvelcompose.ui.navigation.Navigation
import com.jujodevs.marvelcompose.ui.theme.MarvelComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComposeTheme {
                Navigation()
            }
        }
    }
}
