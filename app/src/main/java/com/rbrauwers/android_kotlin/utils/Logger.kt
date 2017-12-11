package com.rbrauwers.android_kotlin.utils

import android.util.Log
import com.rbrauwers.android_kotlin.BuildConfig

/**
 * Created by rodrigobrauwers on 07/12/17.
 */

inline fun log(lambda: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d("DEBUG", lambda())
    }
}