package com.rahul.moviesearch.utils

import com.rahul.moviesearch.BaseTest
import org.junit.Assert
import org.junit.Test

class StringExtTest : BaseTest() {
    @Test
    fun `String should give empty string if value is null`() {
        var value: String? = null
        Assert.assertEquals(value.filterEmpty(), "")
    }

    @Test
    fun `String should not give empty string if value is not null`() {
        var value: String? = "some"
        Assert.assertNotEquals(value.filterEmpty(), "")
    }

    @Test
    fun `should return hours and minutes from the string but not empty`() {
        Assert.assertNotEquals("90".getHoursAndMins(), "")
    }

    @Test
    fun `should return 01 hours and 30 minutes from the string 90 minutes`() {
        Assert.assertEquals("90".getHoursAndMins(), "01:30")
    }

    @Test
    fun `should return 00 hours and 30 minutes from the string 30 minutes`() {
        Assert.assertEquals("30".getHoursAndMins(), "00:30")
    }
}