package com.rahul.moviesearch.features.search.viewmodel

import com.rahul.moviesearch.model.Search

/**
 * View model for the row item of the list which loads data through data binding
 */
class SearchRowViewModel(
    val result: Search? = null,
    private val onSelect: (Search) -> Unit ={}
) {
    fun onClick() {
        result?.let { onSelect.invoke(it) }
    }

}