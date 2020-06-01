package com.rahul.moviesearch.features.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.moviesearch.ServiceType
import com.rahul.moviesearch.model.Search
import com.rahul.moviesearch.network.AppServiceRepo
import com.rahul.moviesearch.utils.filterEmpty
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * ViewModel for the search Activity
 */
class SearchViewModel : ViewModel(), KoinComponent {
    val movieList: MutableLiveData<List<Search>?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    private val appServiceRepo: AppServiceRepo by inject { parametersOf(ServiceType.API) }
    var pagination:Pair<Int,Boolean> = Pair(1,true)

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
                error.postValue(it)

            })
    }

    fun updatePageCount(){
        pagination = if(movieList.value?.size.filterEmpty() <= 10)
            Pair(pagination.first+1,true)
        else Pair(pagination.first,false)
    }

    fun fetchingFirstPage():Boolean{
        return pagination.first == 1
    }

}