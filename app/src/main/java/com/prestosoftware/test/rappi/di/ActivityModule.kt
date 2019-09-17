package com.prestosoftware.test.rappi.di

import com.prestosoftware.test.rappi.ui.main.MainActivity
import com.skydoves.themovies.di.MainActivityFragmentModule
import com.skydoves.themovies.view.ui.details.movie.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

  @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

  @ContributesAndroidInjector
  internal abstract fun contributeMovieDetailActivity(): MovieDetailActivity
}
