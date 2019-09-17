package com.prestosoftware.test.rappi.di

import com.prestosoftware.test.rappi.ui.movie.upcoming.MovieUpcomingListFragment
import com.prestosoftware.test.rappi.ui.movie.popular.MoviePopularListFragment
import com.prestosoftware.test.rappi.ui.movie.topRated.MovieTopListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityFragmentModule {

  @ContributesAndroidInjector
  abstract fun contributeMoviePopularListFragment(): MoviePopularListFragment

  @ContributesAndroidInjector
  abstract fun contributeMovieTopListFragment(): MovieTopListFragment

  @ContributesAndroidInjector
  abstract fun contributeMovieUpcomingListFragment(): MovieUpcomingListFragment

}
