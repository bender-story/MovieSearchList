package com.rahul.moviesearch.network

import com.rahul.moviesearch.BaseTest
import com.rahul.moviesearch.ServiceType
import org.junit.Assert
import org.junit.Test
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class AppServiceRepoTest: BaseTest(){
    private val appServiceRepo: AppServiceRepo by inject{ parametersOf(ServiceType.MOCK) }

    @Test
    fun `get trending latest repository is not null or empty`(){
        appServiceRepo.getMovieDetails(movieID = "some",onSuccess = {
            Assert.assertTrue(it != null)
            Assert.assertEquals(it?.Title,"Batman Beyond: Return of the Joker")
            Assert.assertEquals(it?.imdbRating,"7.8")
            Assert.assertEquals(it?.Language,"English")
        },onError = {
            assert(false)
        })
    }

    @Test
    fun `get trending latest repository size is 2`(){
        appServiceRepo.getMoviesList({
            Assert.assertTrue(it?.size==2)
        },{
            assert(false)
        },"some",1)
    }

    @Test
    fun `get trending latest repository check Values`(){
        appServiceRepo.getMoviesList({
            Assert.assertTrue(it?.get(0)?.Title  =="Batman Beyond: Return of the Joker")
            Assert.assertTrue(it?.get(0)?.Year  =="2000")


            Assert.assertTrue(it?.get(1)?.Type  =="movie")
        },{
            assert(false)
        },"some",1)
    }
}