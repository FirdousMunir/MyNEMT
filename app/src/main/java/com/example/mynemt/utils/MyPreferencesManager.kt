package com.example.mynemt.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.mynemt.R

class MyPreferencesManager {

     var appName : String
     private var context : Context
     var preferences : SharedPreferences

    constructor(mContext: Context){
        this.context = mContext
        this.appName = context.resources.getString(R.string.app_name)
        this.preferences = context.getSharedPreferences(appName,MODE_PRIVATE)
    }

    public fun saveColumn(name:String, value:String){
        val editor : SharedPreferences.Editor = preferences.edit();
        editor.putString(name,value)
        editor.commit()
    }

    public fun fetchColumn(name:String) : String?{
        return preferences.getString(name, "")
    }

    public fun saveTime(name:String, value: Long){
        val editor : SharedPreferences.Editor = preferences.edit();
        editor.putLong(name,value)
        editor.commit()
    }

    public fun fetchTime(name: String): Long {
        return preferences.getLong(name, 0)
    }

}