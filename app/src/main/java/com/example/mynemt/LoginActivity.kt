package com.example.mynemt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mynemt.databinding.ActivityLoginBinding
import com.example.mynemt.model.LoginResponse
import com.example.mynemt.network.RetroService
import com.example.mynemt.utils.MyPreferencesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var editTextName: EditText? = null
    var editTextPassword: EditText? = null
    var editTextCompCode: EditText? = null
    var buttonLogin: Button? = null
    var rememberMeCB: CheckBox? = null
    var name: String? = ""
    var password: String? = ""
    var companyCode: String? = ""
    var savedName: String? = ""
    var savedPassword: String? = ""
    var savedCompanyCode: String? = ""
    var isLogin:String?=""
    private lateinit var binding: ActivityLoginBinding
    lateinit var preferencesManager: MyPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        editTextName = binding.outlinedTextFieldLoginName.editText
        editTextPassword = binding.outlinedTextFieldLoginPassword.editText
        editTextCompCode = binding.outlinedTextFieldCompanyCode.editText
        buttonLogin = binding.buttonDriverLogin
        rememberMeCB = binding.checkboxRememberMe
        preferencesManager = MyPreferencesManager(this@LoginActivity)

        try {
            savedName = preferencesManager.fetchColumn("name")
            savedPassword = preferencesManager.fetchColumn("password")
            savedCompanyCode = preferencesManager.fetchColumn("compCode")
            isLogin = preferencesManager.fetchColumn("isLogin")
            Log.i(TAG, "savedName: $savedName")
            Log.i(TAG, "savedPassword: $savedPassword")
            Log.i(TAG, "savedCompanyCode: $savedCompanyCode")
            Log.i(TAG, "isLogin: $isLogin")
        } catch (e: Exception) {
            Log.i(TAG, "preferencesSavedDataExp: " + e.message)
        }

        if (isLogin == "yes") {
            openMainActivity()
        } else
            Log.i(TAG, "loginResponse: Not Login")

        if (preferencesManager.fetchColumn("isRemember") == "yes") {
            rememberMeCB!!.isChecked = true
            editTextName!!.setText(savedName)
            editTextPassword!!.setText(savedPassword)
            editTextCompCode!!.setText(savedCompanyCode)
        }

        buttonLogin!!.setOnClickListener(View.OnClickListener {

            name = editTextName!!.text.toString().trim()
            password = editTextPassword!!.text.toString().trim()
            companyCode = editTextCompCode!!.text.toString().trim()
            Log.i(TAG, "name:  $name")
            Log.i(TAG, "password:  $password")
            Log.i(TAG, "compCode:  $companyCode")

            if (name!!.isEmpty()) {
                editTextName!!.error = "Name Required"
                editTextName!!.requestFocus()
                return@OnClickListener
            }

            if (password!!.isEmpty()) {
                editTextPassword!!.error = "Password Required"
                editTextPassword!!.requestFocus()
                return@OnClickListener
            }

            if (companyCode!!.isEmpty()) {
                editTextCompCode!!.error = "Company Code Required"
                editTextCompCode!!.requestFocus()
                return@OnClickListener
            }

            val loginBody = mapOf(
                "username" to name,
                "password" to password,
                "device_name" to companyCode
            )

            RetroService.getInstance().driverLogin(loginBody as Map<String, String>)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Log.i(TAG, "responseSuccess:  $response")
                        Log.i(TAG, "responseSuccessBody:  ${response.body()}")
                        Log.i(TAG, "callSuccess:  $call")

                        val token = response.body()!!.token
                        Log.i(TAG, "tokenSuccess:  $token")
                        if (token != null) {
                            if (rememberMeCB!!.isChecked) {
                                preferencesManager.saveColumn("isRemember", "yes")
                                preferencesManager.saveColumn("name", name!!)
                                preferencesManager.saveColumn("password", password!!)
                                preferencesManager.saveColumn("compCode", companyCode!!)
                            } else
                                preferencesManager.saveColumn("isRemember", "no")

                            preferencesManager.saveColumn("loginDriverToken", token)
                            preferencesManager.saveColumn("isLogin","yes")
                            openMainActivity()
                        }

                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.i(TAG, "responseFailure:  ${t.message}")
                        Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                })

        })

    }

    companion object {
        var TAG: String = "LoginActivityTAG"
    }


    private fun openMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

}