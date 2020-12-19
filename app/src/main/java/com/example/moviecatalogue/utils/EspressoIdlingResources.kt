package com.example.moviecatalogue.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResources {
    private const val RESOURCE = "GLOBAL"
    val espressoIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoIdlingResource.increment()
    }

    fun decrement() {
        espressoIdlingResource.decrement()
    }
}