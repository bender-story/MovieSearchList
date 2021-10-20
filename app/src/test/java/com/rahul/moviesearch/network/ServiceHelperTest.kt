package com.rahul.moviesearch.network

import com.rahul.moviesearch.BaseTest
import com.rahul.moviesearch.ServiceType
import org.junit.Assert
import org.junit.Test

class ServiceHelperTest : BaseTest(){

    @Test
    fun `check if instance of ServiceInterface is SimpleInterface`(){
        val serviceAPIHelper=ServiceAPIHelper(ServiceType.API)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is ServiceInterface)
    }

    @Test
    fun `check if instance of ServiceInterface is mockServiceImpl`(){
        val serviceAPIHelper=ServiceAPIHelper(ServiceType.MOCK)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is MockServiceImpl)
    }
}