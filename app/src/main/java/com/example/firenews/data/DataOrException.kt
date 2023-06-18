package com.example.firenews.data

import java.lang.Exception

class DataOrException<T,Boolean,Exception>(
    val data:T? = null,
    val loading:Boolean? = null,
    val exception: Exception? = null
)
