package com.example.firenews.screens.saved

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firenews.components.newsrow.NewsRow

@Composable
fun SavedScreen(navController: NavHostController,savedViewModel: SavedViewModel= hiltViewModel()) {
    Scaffold() {

        savedViewModel.getNewsInRealTime()
        val savedNewsList = savedViewModel.savedNewsList

        Log.d("SavedScreen", "savedNewsList size: ${savedNewsList.value.size} ")

        if (savedNewsList.value.isEmpty()) {
            Box(Modifier.fillMaxSize()) {
                Text("No Saved Articles found")
            }
        } else {
            LazyColumn(Modifier.padding(it)) {
                items(savedNewsList.value){ article->
                    NewsRow(newsArticle = article)
                }


            }
        }
    }

}