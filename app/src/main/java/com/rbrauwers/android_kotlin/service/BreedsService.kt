package com.rbrauwers.android_kotlin.service

import com.rbrauwers.android_kotlin.model.MasterBreedsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

@Singleton
class BreedsService {

    val api: BreedsServiceAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(BreedsServiceAPI::class.java)
    }

    interface BreedsServiceAPI {
        @GET("breeds/list/all")
        fun breeds(): Observable<Any>

        @GET("breeds/list")
        fun masterBreeds(): Observable<MasterBreedsResponse>
    }
}