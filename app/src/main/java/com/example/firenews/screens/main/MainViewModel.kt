package com.example.firenews.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firenews.data.DataOrException
import com.example.firenews.models.Article
import com.example.firenews.models.NewsResponse
import com.example.firenews.models.Source
import com.example.firenews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    var response by mutableStateOf(
        DataOrException(
           data =  NewsResponse(emptyList(),"",-1),
           loading = true,
           exception = Exception("")
        )
    )

    init {
        getNews()
    }

    fun getNews() =
        viewModelScope.launch {

            response = DataOrException(response.data,true,null)

            response = repository.getNews()


        }



}