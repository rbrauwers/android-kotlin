package com.rbrauwers.android_kotlin.breeds_list

/**
 * Created by rodrigobrauwers on 07/12/17.
 */

interface BreedsContract {

    interface View {
        fun showLoadingView()
        fun hideLoadingView()
        fun showError()
        fun showBreeds(breeds: List<String>)
    }

    interface BasePresenter {
        fun loadMasterBreeds()
        fun openBreedDetails(breed: String)
        fun subscribe()
        fun unsubscribe()
    }

}