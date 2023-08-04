package com.example.mynemt.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mynemt.model.LoginResponse
import com.example.mynemt.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel constructor(private val retroService: RetroService) : ViewModel() {

    var loginToken: String? = ""
    var loginError: String? = ""

    fun getDriverLogin(name: String, password: String, companyCode: String) {

        val loginBody = mapOf(
            "username" to name,
            "password" to password,
            "device_name" to companyCode
        )

        retroService.driverLogin(loginBody as Map<String, String>)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val responseBody = response.body()
                    loginToken = responseBody!!.token
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginError = t.message
                }

            })
    }


}