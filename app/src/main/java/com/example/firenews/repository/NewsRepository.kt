package com.example.firenews.repository

import android.util.Log
import com.example.firenews.data.DataOrException
import com.example.firenews.models.NewsResponse
import com.example.firenews.network.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsApi) {

    suspend fun getNews():DataOrException<NewsResponse,Boolean,Exception> {
        val response = try {
             api.getNews()

        }catch (e:Exception){
            return DataOrException(loading = false,exception = e)
        }
        return DataOrException(response,false)

    }

    suspend fun searchNews(searchQuery:String):DataOrException<NewsResponse,Boolean,Exception> {
        val response = try {
            api.searchNews(searchQuery = searchQuery)

        }catch (e:Exception){
            return DataOrException(loading = false,exception = e)
        }
        return DataOrException(response,false)

    }


}