package com.example.firenews.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<BottomNavIcon>
) {
    NavigationBar(
        modifier = modifier,

        ) {
        val backStackEntry = navController.currentBackStackEntryAsState()

        items.forEach {

            val selected = backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = it.route == selected,
                icon = {
                    Icon(it.icon,it.label)
                },
                onClick = {

                        navController.navigate(it.route){
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }



                }
            )
        }


    }
}