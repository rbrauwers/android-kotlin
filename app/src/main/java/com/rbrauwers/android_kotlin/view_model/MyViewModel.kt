package com.rbrauwers.android_kotlin.view_model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rbrauwers.android_kotlin.utils.log
import javax.inject.Inject

/**
 * Created by rodrigobrauwers on 27/03/18.
 */
class MyViewModel @Inject constructor() : ViewModel() {

    val items = MutableLiveData<MutableList<String>>()

    init {
        log{"MyViewModel::init"}
        items.value = ArrayList<String>()
    }

    fun size(): Int {
        return items.value?.size ?: 0
    }

    fun item(index: Int): String {
        return items.value!![index]
    }

    fun addRandom() {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var str = ""
        for (i in 0..10) {
            str += chars[Math.floor(Math.random() * chars.length).toInt()]
        }

        log {"Random ${str} items is null: ${items.value == null}"}

        items.value?.add(str)
        items.postValue(items.value)
    }

}