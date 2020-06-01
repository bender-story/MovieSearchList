package com.rahul.di

import com.rahul.ServiceType
import com.rahul.network.AppServiceRepo
import org.koin.dsl.module
import com.rahul.network.ServiceAPIHelper
/**
 * App Module to load all the Koin injections
 *
 */
val appModule= module {
    factory { (serviceType : ServiceType) -> AppServiceRepo(serviceType) }
    factory { (serviceType : ServiceType) -> ServiceAPIHelper(serviceType) }
//    viewModel { MainViewModel() }
}