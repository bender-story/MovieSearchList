package com.rahul.moviesearch.model

data class MovieSearchResult(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)