package com.rbrauwers.android_kotlin.application

import com.alexfacciorusso.daggerviewmodel.DaggerViewModelInjectionModule
import com.rbrauwers.android_kotlin.di.ActivityBindingModule
import com.rbrauwers.android_kotlin.di.SingletonsModule
import com.rbrauwers.android_kotlin.di.ViewModelsModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, SingletonsModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class, DaggerViewModelInjectionModule::class, ViewModelsModule::class))
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()

}