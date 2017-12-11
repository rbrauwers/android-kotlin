package com.rbrauwers.android_kotlin.breeds_list

import android.app.Activity
import com.rbrauwers.android_kotlin.di.DIAnnotations
import com.rbrauwers.android_kotlin.main_options.MainActivity
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

@Module
abstract class BreedsActivityModule {

    @Binds
    @DIAnnotations.PerActivity
    internal abstract fun activity(activity: BreedsActivity): Activity

}