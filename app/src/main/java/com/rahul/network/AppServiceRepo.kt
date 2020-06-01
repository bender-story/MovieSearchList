package com.rahul.network

import com.rahul.ServiceType
import com.rahul.model.MovieDetailsResult
import com.rahul.model.MovieSearchResult
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * App service repo fetchs data for the viewmodel
 * helps to load data in database
 *
 */

class AppServiceRepo(serviceType: ServiceType):KoinComponent{
    private val serviceAPIHelper: ServiceAPIHelper by inject{ parametersOf(serviceType) }
    /**
     * get movies list from the service
     * @param onSuccess success callback
     * @param onSuccess error callback
     */
    fun getMoviesList(
        onSuccess: (List<MovieSearchResult>?) -> Unit,
        onError: (String) -> Unit,
        movieName: String,
        pageNo: Int
    ){

        serviceAPIHelper.getServiceinterface()!!.fetchMovieList(movieName,pageNo = pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }

    /**
     * get movies details from the service
     * @param onSuccess success callback
     * @param onSuccess error callback
     */
    fun getMovieDetails(
        onSuccess: (List<MovieDetailsResult>?) -> Unit,
        onError: (String) -> Unit,
        movieID: String
    ){

        serviceAPIHelper.getServiceinterface()!!.fetchMovieDetails(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }
}


