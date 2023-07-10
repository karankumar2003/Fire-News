package com.example.firenews.models

import com.google.firebase.firestore.PropertyName

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    @get:PropertyName("published_at")
    @set:PropertyName("published_at")
    var publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    @get:PropertyName("url_to_image")
    @set:PropertyName("url_to_image")
    var urlToImage: String?,
    var uniqueIdentifier: String? = null
) {
    constructor() : this(
        author = null,
        content = null,
        description = null,
        publishedAt = null,
        source = null,
        title = null,
        url = null,
        urlToImage = null,
        uniqueIdentifier = null
    )
}