package com.rbrauwers.android_kotlin.view_model_sample

import android.app.Activity
import com.rbrauwers.android_kotlin.breeds_list.BreedsActivity
import com.rbrauwers.android_kotlin.di.DIAnnotations
import com.rbrauwers.android_kotlin.main_options.MainActivity
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

@Module
abstract class ViewModelActivityModule {

    @Binds
    @DIAnnotations.PerActivity
    internal abstract fun activity(activity: ViewModelActivity): Activity

}