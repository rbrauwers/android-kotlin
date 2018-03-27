package com.rbrauwers.android_kotlin.di

import android.arch.lifecycle.ViewModel
import com.alexfacciorusso.daggerviewmodel.ViewModelKey
import com.rbrauwers.android_kotlin.view_model.MyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by rodrigobrauwers on 27/03/18.
 */
@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    abstract fun bindsMyViewModel(myViewModel: MyViewModel): ViewModel
}
