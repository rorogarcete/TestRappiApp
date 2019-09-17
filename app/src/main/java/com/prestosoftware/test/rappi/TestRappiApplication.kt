package com.prestosoftware.test.rappi

import com.prestosoftware.test.rappi.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class TestRappiApplication: DaggerApplication() {

    companion object {
        const val API_KEY = "2805b177bde136e05a6502ec65f855f0"
        const val API_URL = "https://api.themoviedb.org/3/"
        const val BD_NAME = "movies"

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