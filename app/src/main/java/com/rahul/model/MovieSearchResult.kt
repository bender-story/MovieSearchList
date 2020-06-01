package com.rahul.model

data class MovieSearchResult(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)