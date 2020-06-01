package com.rahul.moviesearch.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// gets the hours and minutes from minutes alone
fun String.getHoursAndMins(): String {
    var sdf = SimpleDateFormat("mm")

    return try {
        val dt: Date = sdf.parse(this)
        sdf = SimpleDateFormat("HH:mm")
        sdf.format(dt)
    } catch (e: ParseException) {
        e.printStackTrace()
        ""
    }
}

fun String?.filterEmpty(): String {
    return this ?: ""
}