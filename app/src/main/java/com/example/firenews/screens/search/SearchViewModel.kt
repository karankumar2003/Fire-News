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



    fun searchNews(searchQuery:String) =
        viewModelScope.launch {
            val response = repository.searchNews(searchQuery)

            newsList = if(response.data!= null){
                response.data.articles
            }else{
                emptyList()
            }
            Log.d("SearchScreen", "SearchViewModel response: ${response.exception?.message}")
        }

}