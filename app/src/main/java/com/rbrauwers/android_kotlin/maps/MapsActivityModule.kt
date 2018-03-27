package com.rbrauwers.android_kotlin.maps

import android.app.Activity
import com.rbrauwers.android_kotlin.di.DIAnnotations
import com.rbrauwers.android_kotlin.main_options.MainActivity
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigobrauwers on 20/03/18.
 */
@Module
abstract class MapsActivityModule {

    @Binds
    @DIAnnotations.PerActivity
    internal abstract fun activity(activity: MapsActivity): Activity

}