package com.example.firenews.data

import java.lang.Exception

class DataOrException<T,Boolean,Exception>(
    val data:T? = null,
    var loading:Boolean? = null,
    val exception: Exception? = null
)
