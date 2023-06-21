package com.example.firenews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firenews.screens.NewsScreens
import com.example.firenews.screens.auth.CreateAccountScreen
import com.example.firenews.screens.auth.LogInScreen
import com.example.firenews.screens.main.MainScreen
import com.example.firenews.screens.splash.SplashScreen

@Composable
fun NewsAppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NewsScreens.SplashScreen.name){

        composable(NewsScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(NewsScreens.LogInScreen.name){
            LogInScreen(navController = navController)
        }
        composable(NewsScreens.CreateAccountScreen.name){
            CreateAccountScreen(navController = navController)
        }
        composable(NewsScreens.MainScreen.name){
            MainScreen(authNavController = navController)
            //AuthNavController is the outer navController( with logInScreen)
        }

    }

}