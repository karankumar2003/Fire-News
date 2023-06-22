package com.example.firenews.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firenews.models.Article
import com.example.firenews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    var newsList by mutableStateOf<List<Article>>(emptyList())
    private var page = 2

    var searchLoading by mutableStateOf(false)

    fun searchNews(searchQuery:String) =
        viewModelScope.launch {
            searchLoading = true
            val response = repository.searchNews(searchQuery)

            newsList = response.data?.articles ?: emptyList()
            searchLoading = false

        }

    var isPaginationLoading by mutableStateOf(false)


    fun searchNextPage(searchQuery:String) =
        viewModelScope.launch {
            isPaginationLoading = true
            val response = repository.searchNews(searchQuery, page = 2)
            newsList += response.data?.articles ?: emptyList()
            isPaginationLoading = false
            page++
            Log.d("MainViewModel", "getNextPage: $page")
        }

}