package com.prestosoftware.test.rappi.di

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ComposeModule {
  @ContributesAndroidInjector
  internal abstract fun contributeViewModelActivity(): ViewModelActivity

  @ContributesAndroidInjector
  internal abstract fun contributeViewModelFragment(): ViewModelFragment
}
