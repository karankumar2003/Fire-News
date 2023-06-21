package com.example.firenews.screens.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firenews.components.NewsAppBar
import com.example.firenews.components.newsrow.NewsRow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            NewsAppBar(title = "Search News", isMainScreen = false, navController = navController)
        }
    ) {
        Column(Modifier.padding(10.dp)) {
            var searchQuery by remember {
                mutableStateOf("")
            }
            val listState = rememberLazyListState()

            SearchField(
                onSearch = {
                    searchViewModel.searchNews(it)
                    searchQuery = it
                    scope.launch {
                        listState.scrollToItem(0)
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
            )

            Spacer(modifier = Modifier.height(10.dp))

            val newsList = searchViewModel.newsList

            Log.d("SearchScreen", "SearchScreen: ${newsList.size}")
            LazyColumn(Modifier.fillMaxSize(), state = listState) {


                itemsIndexed(newsList) { index, article ->

                    if (index == searchViewModel.newsList.lastIndex) {
                        searchViewModel.searchNextPage(searchQuery)

                    }

                    NewsRow(newsArticle = article)
                }
                if (searchViewModel.isPaginationLoading) {
                    item {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.BottomCenter))
                        }
                    }
                }

            }

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchState = rememberSaveable {
        mutableStateOf("")
    }

    val isValid = remember(searchState.value) {
        searchState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        searchState.value,
        {
            searchState.value = it
        },
        modifier,
        label = {
            Text(text = "Search News")
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.clickable {
                    onSearch(searchState.value)
                }
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        keyboardActions = KeyboardActions {
            if (!isValid) {
                return@KeyboardActions
            } else {
                onSearch(searchState.value)
                keyboardController?.hide()
            }
        }
    )
}