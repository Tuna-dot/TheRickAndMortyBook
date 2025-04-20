package com.example.therickandmortybook.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.therickandmortybook.ui.navigate.MainScreenNavHost
import com.example.therickandmortybook.ui.theme.TheRickAndMortyBookTheme
import com.example.therickandmortybook.ui.topbar.CenteredTopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var title = remember { mutableStateOf("Rick and Morty") }
            val onBackClick = remember { mutableStateOf<(() -> Unit)?>(null) }
            TheRickAndMortyBookTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenteredTopBar(
                            title = title.value,
                            onBackClick = onBackClick.value
                        )
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        MainScreenNavHost(title = title, onBackClick = onBackClick)
                    }
                }
            }
        }
    }
}


