package com.rahul.moviesearch.features.movie_details.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.moviesearch.ServiceType
import com.rahul.moviesearch.model.MovieDetailsResult
import com.rahul.moviesearch.network.AppServiceRepo
import com.rahul.moviesearch.utils.filterEmpty
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MovieDetailsViewModel : ViewModel(), KoinComponent {
    val movieDetails: ObservableField<MovieDetailsResult> = ObservableField()
    val error: MutableLiveData<String?> = MutableLiveData()
    private val appServiceRepo: AppServiceRepo by inject { parametersOf(ServiceType.API) }
    /**
     *  call service to fetch movie list
     */
    fun fetchMovieDetails(
        movieId: String?
    ) {
        appServiceRepo.getMovieDetails(movieID = movieId.filterEmpty(),
            onSuccess = { response ->
                movieDetails.set(response)
            }, onError = {
                error.postValue(it)

            })
    }

    override fun onCleared() {
        appServiceRepo.dispose()
        super.onCleared()
    }
}