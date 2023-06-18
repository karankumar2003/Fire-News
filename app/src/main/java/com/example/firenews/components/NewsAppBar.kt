package com.example.firenews.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import java.util.LinkedList
import java.util.Queue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(
    title:String,
    isMainScreen: Boolean,
    navController:NavHostController

) {
    CenterAlignedTopAppBar(
        title = {
                Text(title)
        },
        navigationIcon = {
            if(!isMainScreen){
                IconButton(onClick = { navController.navigateUp()}) {
                    Icon(Icons.Default.ArrowBack,"Go Back")
                }
            }
        }

    )
}
