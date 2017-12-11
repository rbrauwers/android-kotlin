package com.rbrauwers.android_kotlin.extension

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import android.view.ViewPropertyAnimator
import kotlinx.android.synthetic.main.activity_breeds.*

/**
 * Created by rodrigobrauwers on 07/12/17.
 */

const val SHOW_HIDE_DURATION: Long = 500

fun View.showFromTop() {
    clearAnimation()
    visibility = View.VISIBLE
    translationY = (-getHeight()).toFloat()
    ViewCompat.animate(this)
            .translationY(0f)
            .setDuration(SHOW_HIDE_DURATION)
            .start()
}

fun View.hideToTop() {
    clearAnimation()
    val propertyAnimator = ViewCompat.animate(this)
            .translationY((-getHeight()).toFloat())
            .setDuration(SHOW_HIDE_DURATION)
    propertyAnimator
            .setListener(object: ViewPropertyAnimatorListener {
                override fun onAnimationEnd(view: View?) {
                    visibility = View.INVISIBLE
                    propertyAnimator.setListener(null)
                }

                override fun onAnimationCancel(view: View?) {

                }

                override fun onAnimationStart(view: View?) {

                }
            })
            .start()
}

