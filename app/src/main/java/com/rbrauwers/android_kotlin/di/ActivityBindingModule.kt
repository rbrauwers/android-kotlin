package com.rbrauwers.android_kotlin.di

import com.rbrauwers.android_kotlin.breeds_list.BreedsActivity
import com.rbrauwers.android_kotlin.breeds_list.BreedsActivityModule
import com.rbrauwers.android_kotlin.main_options.MainActivity
import com.rbrauwers.android_kotlin.main_options.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

@Module
abstract class ActivityBindingModule {

    @get:DIAnnotations.PerActivity
    @get:ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract val mainActivityInjector: MainActivity

    @get:DIAnnotations.PerActivity
    @get:ContributesAndroidInjector(modules = arrayOf(BreedsActivityModule::class))
    internal abstract val breedsActivityInjector: BreedsActivity

}