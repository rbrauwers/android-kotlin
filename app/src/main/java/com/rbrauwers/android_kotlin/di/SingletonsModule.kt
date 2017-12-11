package com.rbrauwers.android_kotlin.di

import com.rbrauwers.android_kotlin.helper.SharedPreferencesHelper
import com.rbrauwers.android_kotlin.application.MyApplication
import com.rbrauwers.android_kotlin.data.BreedsRepository
import com.rbrauwers.android_kotlin.service.BreedsService
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigobrauwers on 06/12/17.
 */
@Module
class SingletonsModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(context: MyApplication): SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideBreedsService(): BreedsService {
        return BreedsService()
    }

    @Provides
    @Singleton
    @Inject
    fun provideBreedsRepository(breedsService: BreedsService) : BreedsRepository {
        return BreedsRepository(breedsService)
    }

}