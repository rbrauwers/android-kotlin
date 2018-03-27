package com.rbrauwers.android_kotlin.di

import com.rbrauwers.android_kotlin.breeds_list.BreedsActivity
import com.rbrauwers.android_kotlin.breeds_list.BreedsActivityModule
import com.rbrauwers.android_kotlin.main_options.MainActivity
import com.rbrauwers.android_kotlin.main_options.MainActivityModule
import com.rbrauwers.android_kotlin.maps.MapsActivity
import com.rbrauwers.android_kotlin.maps.MapsActivityModule
import com.rbrauwers.android_kotlin.view_model_sample.ViewModelActivity
import com.rbrauwers.android_kotlin.view_model_sample.ViewModelActivityModule
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

    @get:DIAnnotations.PerActivity
    @get:ContributesAndroidInjector(modules = arrayOf(MapsActivityModule::class))
    internal abstract val mapsActivityInjector: MapsActivity

    @get:DIAnnotations.PerActivity
    @get:ContributesAndroidInjector(modules = arrayOf(ViewModelActivityModule::class))
    internal abstract val viewModelActivityInjector: ViewModelActivity

}