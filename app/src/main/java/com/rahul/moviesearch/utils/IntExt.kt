package com.rahul.moviesearch.utils

fun Int?.filterEmpty() :Int{
    return this ?: 0
}