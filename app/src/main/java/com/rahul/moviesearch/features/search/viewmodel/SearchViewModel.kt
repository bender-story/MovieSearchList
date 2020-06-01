package com.rahul.moviesearch.features.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.moviesearch.ServiceType
import com.rahul.moviesearch.model.Search
import com.rahul.moviesearch.network.AppServiceRepo
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * ViewModel for the search Activity
 */
class SearchViewModel : ViewModel(), KoinComponent {
    val movieList: MutableLiveData<List<Search>?> = MutableLiveData()
    private val appServiceRepo: AppServiceRepo by inject { parametersOf(ServiceType.API) }

    /**
     *  call service to fetch movie list
     */
    fun fetchMovieList(
        movieName: String,
        pageNo: Int
    ) {
        appServiceRepo.getMoviesList(movieName = movieName, pageNo = pageNo,
            onSuccess = { response ->
                movieList.postValue(response)
            }, onError = {


            })
    }

    /**
     *  Create a MainRowViewModel list from the mutable list
     */
    fun getSearchRowViewModel(): List<SearchRowViewModel>? {
        return movieList.value?.map {
            SearchRowViewModel(it)
        }
    }

}