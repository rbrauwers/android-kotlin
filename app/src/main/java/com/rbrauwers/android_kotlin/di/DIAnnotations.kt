package com.rbrauwers.android_kotlin.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by rodrigobrauwers on 06/12/17.
 */

class DIAnnotations {

    /**
     * A custom scoping annotation that specifies that the lifespan of a dependency be the same as that
     * of an Activity.
     *
     * This is used to annotate dependencies that behave like a singleton within the lifespan of an
     * Activity, Fragment, and child Fragments instead of the entire Application.
     */
    @Scope
    @kotlin.annotation.Retention
    annotation class PerActivity

    /**
     * A custom scoping annotation that specifies that the lifespan of a dependency be the same as that
     * of a child Fragment (a fragment inside a fragment that is added using
     * Fragment.getChildFragmentManager().
     *
     *
     * This is used to annotate dependencies that behave like a singleton within the lifespan of a
     * child Fragment instead of the entire Application, Activity, or parent Fragment.
     *
     *
     * Note that this does not support a child fragment within a child fragment as conflicting scopes
     * will occur. Child fragments within child fragments should usually be avoided. However, if
     * another level of child fragment is required, then another scope would need to be created
     * (perhaps PerGrandChild custom scope annotation).
     */
    @Scope
    @kotlin.annotation.Retention
    annotation class PerChildFragment

    /**
     * A custom scoping annotation that specifies that the lifespan of a dependency be the same as that
     * of a Fragment.
     *
     * This is used to annotate dependencies that behave like a singleton within the lifespan of a
     * Fragment and child Fragments instead of the entire Application or Activity.
     */
    @Scope
    @kotlin.annotation.Retention
    annotation class PerFragment

}