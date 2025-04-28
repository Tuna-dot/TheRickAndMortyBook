package com.example.therickandmortybook.ui.bar.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.therickandmortybook.ui.navigate.NavigateScreen

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?
) {
    NavigationBar {
        val items = listOf(
            Triple(NavigateScreen.Character, "Персонажи", Icons.AutoMirrored.Filled.List),
            Triple(NavigateScreen.Location, "Локации", Icons.Default.Place),
            Triple(NavigateScreen.Episode, "Эпизоды", Icons.Outlined.MailOutline),
            Triple(NavigateScreen.Favorite, "Избранное", Icons.Default.Star),
        )

        items.forEach { (screen, label, icon) ->

            NavigationBarItem(
                selected = currentRoute?.startsWith(screen.route.substringBefore("/")) == true,
                onClick = {

                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(text = label)
                }
            )
        }
    }
}