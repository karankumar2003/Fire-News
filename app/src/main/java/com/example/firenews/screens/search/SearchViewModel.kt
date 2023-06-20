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
    var page = 2



    fun searchNews(searchQuery:String) =
        viewModelScope.launch {
            val response = repository.searchNews(searchQuery)


            newsList = response.data?.articles ?: emptyList()

        }

    fun searchNextPage(searchQuery:String) =
        viewModelScope.launch {
            val response = repository.searchNews(searchQuery, page = page)

            newsList += response.data?.articles ?: emptyList()
            page++
            Log.d("MainViewModel", "getNextPage: $page")
        }

}