package com.rahul.moviesearch.utils

import com.rahul.moviesearch.BaseTest
import org.junit.Assert
import org.junit.Test

class IntExtTest: BaseTest(){
    @Test
    fun `Int should give 0 if value is empty`(){
        var value :Int? = null
        Assert.assertEquals(value.filterEmpty(),0)
    }

    @Test
    fun `Int should not give 0 if value is not empty`(){
        var value :Int? = 1
        Assert.assertNotEquals(value.filterEmpty(),0)
    }
}