package com.rbrauwers.android_kotlin.helper

import android.content.Context
import android.content.SharedPreferences
import com.rbrauwers.android_kotlin.application.MyApplication

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

class SharedPreferencesHelper(applicationContext: MyApplication) {

    val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = applicationContext.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)
    }
}