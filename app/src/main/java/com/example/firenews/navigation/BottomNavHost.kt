package com.example.firenews.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firenews.screens.home.HomeScreen
import com.example.firenews.screens.NewsScreens
import com.example.firenews.screens.saved.SavedScreen
import com.example.firenews.screens.search.SearchScreen
import com.example.firenews.screens.setting.SettingScreen


@Composable
fun BottomNavHost(
    authNavController:NavHostController,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {


    NavHost(
        navController = navController,
        startDestination = NewsScreens.HomeScreen.name,
        modifier = modifier
    ) {

        composable(NewsScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(NewsScreens.SavedScreen.name) {
            SavedScreen(navController)
        }
        composable(NewsScreens.SettingScreen.name) {
            SettingScreen(navController=navController,authNavController = authNavController)
        }
        composable(NewsScreens.SearchScreen.name) {
            SearchScreen(navController= navController)
        }
    }
}