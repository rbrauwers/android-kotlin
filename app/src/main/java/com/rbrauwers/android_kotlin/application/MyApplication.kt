package com.rbrauwers.android_kotlin.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

class MyApplication : DaggerApplication() {

    protected lateinit var appInjector: AndroidInjector<MyApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appInjector
    }

    override fun onCreate() {
        // TODO
        appInjector = DaggerAppComponent.builder().create(this);
        appInjector.inject(this);
        super.onCreate()
    }
}