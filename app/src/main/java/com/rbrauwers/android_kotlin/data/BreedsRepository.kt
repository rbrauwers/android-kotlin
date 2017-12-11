package com.rbrauwers.android_kotlin.data

import android.util.Log
import com.rbrauwers.android_kotlin.model.MasterBreedsResponse
import com.rbrauwers.android_kotlin.service.BreedsService
import com.rbrauwers.android_kotlin.utils.log
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.operators.observable.ObservableCreate
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigobrauwers on 07/12/17.
 */
@Singleton
class BreedsRepository(val breedsService: BreedsService) {

    var masterBreedsResponse: MasterBreedsResponse? = null

    /*
        Returns a observable with data from cache if it exists.
        Returns a observable with data from network otherwise.
        How it works:
            - empty observables does not trigger onNext
            - therefore, take(1) will finish data emission only when there is some response (either from cache or network)
     */
    fun masterBreedsObservable() : Observable<MasterBreedsResponse> {
        return breedsService
                .api
                .masterBreeds()
                .startWith(masterBreedsCacheObservable())
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    response -> masterBreedsResponse = response
                }
    }

    private fun masterBreedsCacheObservable() : Observable<MasterBreedsResponse> {
        if (masterBreedsResponse == null) {
            return Observable.empty()
        }
        else {
            return Observable.just(masterBreedsResponse!!)
        }
    }

}