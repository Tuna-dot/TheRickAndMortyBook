package com.example.therickandmortybook.ui.bar.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import com.example.therickandmortybook.ui.navigate.NavigateScreen

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?
){
    NavigationBar {
       NavigationBarItem(
           selected = currentRoute == NavigateScreen.Character.route,
           onClick = {
               navController.navigate(NavigateScreen.Character.route){
                   popUpTo(0)
               }
           },
           icon = {
               Icon(
                   imageVector = Icons.AutoMirrored.Default.List,
                   contentDescription = "Character"
               )
           },
           label = {
                Text(text = "Список персонажей")
           }
       )
    }
}