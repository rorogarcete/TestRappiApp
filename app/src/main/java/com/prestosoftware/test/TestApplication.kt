package com.prestosoftware.test

import com.prestosoftware.test.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class TestApplication: DaggerApplication() {

    companion object {
        const val API_KEY = "2805b177bde136e05a6502ec65f855f0"
        const val API_LG = "en-US"
        const val API_URL = "https://api.themoviedb.org/3/"
        const val POST_API_URL = "https://hn.algolia.com/api/v1/"
        const val BD_NAME = "movies"

        const val IS_POST_SERVICE_ON = true

        const val CATEGORY_POPULAR = "popular"
        const val CATEGORY_TOP = "top_rated"
        const val CATEGORY_UPCOMING = "upcoming"
    }

    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

}