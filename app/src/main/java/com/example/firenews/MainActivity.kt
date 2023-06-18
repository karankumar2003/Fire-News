package com.example.firenews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.firenews.navigation.BottomNavHost
import com.example.firenews.navigation.BottomNavIcon
import com.example.firenews.navigation.BottomNavigation
import com.example.firenews.screens.NewsScreens
import com.example.firenews.ui.theme.FireNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navItems = listOf(
                        BottomNavIcon(
                            "Home",
                            NewsScreens.MainScreen.name,
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

                    Scaffold(
                        bottomBar = {
                            BottomNavigation(
                                navController = navController,
                                items = navItems,
                                onNavIconClick = {
                                    navController.navigate(it.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = false
                                        }
                                    }
                                })
                        }
                    ) {
                        BottomNavHost(
                            navController = navController,
                            modifier = Modifier.padding(it)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FireNewsTheme {


    }
}