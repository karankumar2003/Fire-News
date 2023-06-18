package com.example.firenews.screens.main

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
class MainViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    var newsList by mutableStateOf<List<Article>>(emptyList())


    init{
        getNews()
    }

    fun getNews() =
        viewModelScope.launch {
            val response = repository.getNews().data

            newsList = if(response!= null){
                response.articles
            }else{
                emptyList()
            }

        }

}