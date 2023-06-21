package com.example.firenews.models

data class Source(
    val id: String?,
    val name: String?
){
    constructor() : this(
    null,
    null
    )
}