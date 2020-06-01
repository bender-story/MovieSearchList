package com.rahul.moviesearch.di

import com.rahul.moviesearch.ServiceType
import com.rahul.moviesearch.network.AppServiceRepo
import org.koin.dsl.module
import com.rahul.moviesearch.network.ServiceAPIHelper
/**
 * App Module to load all the Koin injections
 *
 */
val appModule= module {
    factory { (serviceType : ServiceType) -> AppServiceRepo(serviceType) }
    factory { (serviceType : ServiceType) -> ServiceAPIHelper(serviceType) }
//    viewModel { MainViewModel() }
}