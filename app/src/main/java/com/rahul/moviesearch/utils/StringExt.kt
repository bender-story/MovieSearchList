package com.rahul.moviesearch.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.getHoursAndMins(): String {
    var sdf = SimpleDateFormat("mm")

    try {
        val dt: Date = sdf.parse(this)
        sdf = SimpleDateFormat("HH:mm")
        return sdf.format(dt)
    } catch (e: ParseException) {
        e.printStackTrace()
        return ""
    }
}

fun String?.filterEmpty():String{
    return this ?: ""
}