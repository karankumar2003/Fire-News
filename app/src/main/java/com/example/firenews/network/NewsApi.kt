package com.example.firenews.network

import com.example.firenews.models.NewsResponse
import com.example.firenews.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("category") category : String = "general"
    ): NewsResponse

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("q") searchQuery : String = "",
        @Query("pageSize") pageSize : Int = 20
    ): NewsResponse
}