package com.example.mynemt.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

class TimeDateParser {

    fun currentPickupTime(timeDate: String?): String? {

//        "2023-03-24 07:44:00";
        var formattedDate = ""
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        try {
            val date = formatter.parse(timeDate)
            Log.i(TAG, " date: $date")
            formattedDate = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(date)
            Log.i(TAG, " formattedDate: $formattedDate")
        } catch (e: Exception) {
            Log.i(TAG, " DateExp: " + e.message)
        }
        return formattedDate
    }

    fun todayDateForCurrentDayTrips(): String? {
//        2023-03-24
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        Log.i(TAG, "CurrentDayDate: $todayDate")
        return todayDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextDayDateForCurrentDayTrips(): String? {
        val todayDate = LocalDate.now()
        val nextDay = todayDate.plusDays(1).toString()
        Log.i(TAG, "CNextDayDate: $nextDay")
        return nextDay
    }

    companion object{
        var TAG = "TimeDateParser"
    }

}