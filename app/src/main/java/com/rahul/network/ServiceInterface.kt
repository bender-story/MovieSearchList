package com.rahul.network

import com.rahul.Constants
import com.rahul.model.MovieDetailsResult
import com.rahul.model.MovieSearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service Interface to fetch data
 *
 */
interface ServiceInterface {
    /**
     * get movies list from the service
     */
    @GET("?")
    fun fetchMovieList(
        @Query("s") movieName: String,
        @Query("page") pageNo: Int,
        @Query("apikey") apiKey: String = Constants.apikey
    ): Observable<List<MovieSearchResult>>


    /**
     * get movies details from the service
     */
    @GET("?")
    fun fetchMovieDetails(
        @Query("i") movieID: String,
        @Query("apikey") apiKey: String = Constants.apikey
    ): Observable<List<MovieDetailsResult>>
}