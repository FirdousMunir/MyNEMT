package com.example.mynemt.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynemt.utils.MyPreferencesManager

class HomeViewModel : ViewModel() {

    private lateinit var preferencesManager: MyPreferencesManager

    public fun initManager(pManager : MyPreferencesManager){
        preferencesManager = pManager
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    private val _isClockIn = MutableLiveData<String>().apply {
        value = preferencesManager.fetchColumn("UserClockIn")
    }

    val icClockIn :LiveData<String> = _isClockIn




}