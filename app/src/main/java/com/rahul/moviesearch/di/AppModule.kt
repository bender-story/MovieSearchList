package com.rahul.moviesearch.di

import com.rahul.moviesearch.ServiceType
import com.rahul.moviesearch.features.movie_details.viewmodel.MovieDetailsViewModel
import com.rahul.moviesearch.features.search.viewmodel.SearchViewModel
import com.rahul.moviesearch.network.AppServiceRepo
import org.koin.dsl.module
import com.rahul.moviesearch.network.ServiceAPIHelper
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * App Module to load all the Koin injections
 *
 */
val appModule= module {
    factory { (serviceType : ServiceType) -> AppServiceRepo(serviceType) }
    factory { (serviceType : ServiceType) -> ServiceAPIHelper(serviceType) }
    viewModel { SearchViewModel() }
    viewModel { MovieDetailsViewModel() }
}