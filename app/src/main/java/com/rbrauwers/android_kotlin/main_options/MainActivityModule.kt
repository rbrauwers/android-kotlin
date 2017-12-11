package com.rbrauwers.android_kotlin.main_options

import android.app.Activity
import com.rbrauwers.android_kotlin.di.DIAnnotations
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

@Module
abstract class MainActivityModule {

    @Binds
    @DIAnnotations.PerActivity
    internal abstract fun activity(activity: MainActivity): Activity

}