package com.example.firenews.screens.main

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firenews.components.NewsRow
import com.example.firenews.models.Article

@OptIn( ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val response = mainViewModel.response
    val context = LocalContext.current
    Log.d("SearchScreen", "MainScreen: ${response.loading}")


    val pullRefreshState  = rememberPullRefreshState(
        refreshing = response.loading ?: false,
        onRefresh = {
            mainViewModel.getNews()
            Toast.makeText(context,"Refreshing",Toast.LENGTH_SHORT).show()
    }
    )


    if (response.loading == true) {

        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

    } else {

        Box(

        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)

            ) {

                itemsIndexed(items = response.data?.articles!!) { index: Int, item: Article ->
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

            PullRefreshIndicator(
                refreshing = response.loading ?: false,
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

