package com.rbrauwers.android_kotlin.extension

import android.content.Intent
import android.support.v7.app.AppCompatActivity

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

fun AppCompatActivity.startActivityWithClass(someClass: Class<*>) {
    intent = Intent(applicationContext, someClass)
    startActivity(intent)
}
