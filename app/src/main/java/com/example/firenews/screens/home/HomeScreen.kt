package com.example.firenews.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firenews.components.newsrow.NewsRow
import com.example.firenews.models.Article

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    mainViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val response = mainViewModel.result.collectAsState()

    val context = LocalContext.current


    val pullRefreshState = rememberPullRefreshState(
        refreshing = response.value.loading!! && !response.value.data?.articles.isNullOrEmpty(),
        onRefresh = {
            mainViewModel.getNews()
            Toast.makeText(context, "Refreshing", Toast.LENGTH_SHORT).show()
        }
    )


    if (response.value.loading == true && response.value.data?.articles.isNullOrEmpty()) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

    } else {

        Box {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)

            ) {

                itemsIndexed(items = mainViewModel.list.value) { index: Int, item: Article ->
                    Log.d("HomeScreen", "HomeScreen: ${mainViewModel.list.value.size}")
                    Log.d("HomeScreen", "HomeScreen: index ${index}")

                    if(index == mainViewModel.list.value.lastIndex){

                            mainViewModel.getNextPage()

                    }

                    NewsRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        item
                    )
                }
                if (mainViewModel.isPaginationLoading){
                    item {
                        Box(Modifier.fillMaxSize()){
                            CircularProgressIndicator(Modifier.align(Alignment.BottomCenter))
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = response.value.loading ?: false,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )


        }
    }
//    NewsAppBar(
//        title = "Home",
//        isMainScreen = true,
//        navController = navController,
//
//    )

}

