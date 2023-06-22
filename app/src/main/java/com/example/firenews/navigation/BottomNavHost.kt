package com.example.firenews.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firenews.screens.home.HomeScreen
import com.example.firenews.screens.AppScreens
import com.example.firenews.screens.saved.SavedScreen
import com.example.firenews.screens.search.SearchScreen
import com.example.firenews.screens.setting.SettingScreen


@Composable
fun BottomNavHost(
    mainNavController:NavHostController,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {


    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name,
        modifier = modifier
    ) {

        composable(AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(AppScreens.SavedScreen.name) {
            SavedScreen(navController)
        }
        composable(AppScreens.SettingScreen.name) {
            SettingScreen(navController=navController, mainNavController = mainNavController)
        }
        composable(AppScreens.SearchScreen.name) {
            SearchScreen(navController= navController)
        }
    }
}