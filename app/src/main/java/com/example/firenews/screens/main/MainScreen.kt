package com.example.firenews.screens.main

import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firenews.components.NewsAppBar
import com.example.firenews.components.NewsRow
import com.example.firenews.models.Article

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            NewsAppBar(title = "Home", isMainScreen = true, navController = navController)
        }
    ) {
        val newsList = mainViewModel.newsList
        val context = LocalContext.current
        Log.d("SearchScreen", "MainScreen: ${newsList.size}")
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            itemsIndexed(items = newsList) { index: Int, item: Article ->
                NewsRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            val intent = CustomTabsIntent
                                .Builder()
                                .build()
                            intent.intent.`package` = "com.android.chrome"
                            intent.launchUrl(context, Uri.parse(item.url))

                        },
                    item
                )
            }
        }
    }

}