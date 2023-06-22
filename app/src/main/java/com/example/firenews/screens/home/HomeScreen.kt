package com.example.firenews.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firenews.components.NewsAppBar
import com.example.firenews.components.newsrow.NewsRow
import com.example.firenews.models.Article

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 0.dp) {
                NewsAppBar(
                    title = "Home",
                    isMainScreen = true,
                    navController = navController,
                    )
            }
        }

    ) {
        val response = homeViewModel.result.collectAsState()

        val context = LocalContext.current


        val pullRefreshState = rememberPullRefreshState(
            refreshing = response.value.loading!! && !response.value.data?.articles.isNullOrEmpty(),
            onRefresh = {
                homeViewModel.getNews()
                Toast.makeText(context, "Refreshing", Toast.LENGTH_SHORT).show()
            }
        )


        if (response.value.loading == true && response.value.data?.articles.isNullOrEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

        } else {

            Box(Modifier.padding(it)) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(pullRefreshState)

                ) {

                    itemsIndexed(items = homeViewModel.list.value) { index: Int, item: Article ->
                        Log.d("HomeScreen", "HomeScreen: ${homeViewModel.list.value.size}")
                        Log.d("HomeScreen", "HomeScreen: index ${index}")

                        if (index == homeViewModel.list.value.lastIndex) {

                            homeViewModel.getNextPage()

                        }

                        NewsRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                            item
                        )
                    }
                    if (homeViewModel.isPaginationLoading) {
                        item {
                            Box(Modifier.fillMaxSize()) {
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


    }

}