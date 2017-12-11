package com.rbrauwers.android_kotlin.application

import android.app.Application
import com.rbrauwers.android_kotlin.application.MyApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

@Module
abstract class AppModule {

    @Binds
    @Singleton
    internal abstract/*
     * Singleton annotation isn't necessary since Application instance is unique but is here for
     * convention. In general, providing Activity, Fragment, BroadcastReceiver, etc does not require
     * them to be scoped since they are the components being injected and their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     *///abstract UnitTestsApplication application(UnitTestsApplication app);
    fun application(app: MyApplication): Application
}