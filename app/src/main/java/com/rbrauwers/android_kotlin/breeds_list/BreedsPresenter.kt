package com.rbrauwers.android_kotlin.breeds_list

import com.rbrauwers.android_kotlin.data.BreedsRepository
import com.rbrauwers.android_kotlin.model.MasterBreedsResponse
import com.rbrauwers.android_kotlin.utils.log
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by rodrigobrauwers on 07/12/17.
 */

class BreedsPresenter(private val repository: BreedsRepository, private val view: BreedsContract.View): BreedsContract.BasePresenter {

    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun loadMasterBreeds() {
        disposable.clear()
        view.showLoadingView()

        val d = repository
            .masterBreedsObservable()
            .doOnError { view.showError() }
            .subscribe(
                {   response ->
                    view.showBreeds(response.message) },
                {   error ->
                    view.hideLoadingView()})

        disposable.add(d)
    }

    override fun openBreedDetails(breed: String) {
        log { "Open breed: ${breed}" }
    }

    override fun subscribe() {
        loadMasterBreeds()
    }

    override fun unsubscribe() {
        disposable.clear()
    }

}