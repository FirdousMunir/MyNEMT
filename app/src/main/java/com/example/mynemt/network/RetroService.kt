package com.example.mynemt.network

import com.example.mynemt.model.LoginResponse
import com.example.mynemt.model.TripModel
import okhttp3.FormBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroService {


    @POST("driver/auth/login")
    fun driverLogin(@Body body: Map<String, String>): Call<LoginResponse>

    @GET("driver/tasks")
    @Headers("Accept: application/json")
    fun getAllCurrentDayTrips( @Query("date")  date:String , @Header("Authorization") token :String ):Call<List<TripModel>>

    companion object {
        var retrofitService: RetroService? = null

        fun getInstance(): RetroService {
            if (retrofitService == null) {
                val retrofit= Retrofit.Builder().baseUrl("https://api-bot.mynemtbot.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService= retrofit.create(RetroService::class.java)
            }
            return retrofitService!!
        }
    }

}