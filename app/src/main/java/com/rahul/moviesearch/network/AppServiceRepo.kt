package com.rahul.moviesearch.network

import com.rahul.moviesearch.ServiceType
import com.rahul.moviesearch.model.MovieDetailsResult
import com.rahul.moviesearch.model.Search
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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

    // initialise disposable object to dump api calls
    private val disposable: CompositeDisposable = CompositeDisposable()
    /**
     * get movies list from the service
     * @param onSuccess success callback
     * @param onSuccess error callback
     */
    fun getMoviesList(
        onSuccess: (List<Search>?) -> Unit,
        onError: (String) -> Unit,
        movieName: String,
        pageNo: Int
    ){

        disposable.add(serviceAPIHelper.getServiceinterface()!!.fetchMovieList(movieName,pageNo = pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result.Search)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            ))
    }

    /**
     * get movies details from the service
     * @param onSuccess success callback
     * @param onSuccess error callback
     */
    fun getMovieDetails(
        onSuccess: (MovieDetailsResult?) -> Unit,
        onError: (String) -> Unit,
        movieID: String
    ){

        disposable.add( serviceAPIHelper.getServiceinterface()!!.fetchMovieDetails(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            ))
    }

    /**
     * method to dump calls and release memory
     */
    fun dispose() {
        disposable.dispose()
    }
}


