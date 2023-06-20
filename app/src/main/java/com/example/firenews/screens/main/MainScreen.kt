package com.example.firenews.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firenews.navigation.BottomNavHost
import com.example.firenews.navigation.BottomNavIcon
import com.example.firenews.navigation.BottomNavigationBar
import com.example.firenews.screens.NewsScreens

@Composable
fun MainScreen() {

    val navItems = listOf(
        BottomNavIcon(
            "Home",
            NewsScreens.HomeScreen.name,
            Icons.Default.Home
        )
        ,
        BottomNavIcon(
            "Search",
            NewsScreens.SearchScreen.name,
            Icons.Default.Search
        ),
        BottomNavIcon(
            "Saved",
            NewsScreens.SavedScreen.name,
            Icons.Default.Favorite
        ),
        BottomNavIcon(
            "Settings",
            NewsScreens.SettingScreen.name,
            Icons.Default.Settings
        )
    )
val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = navItems,
            )
        }
    ) {
        BottomNavHost(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}