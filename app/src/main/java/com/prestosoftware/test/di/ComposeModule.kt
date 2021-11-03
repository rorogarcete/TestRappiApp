package com.prestosoftware.test.di

import com.prestosoftware.test.rappi.util.compose.ViewModelActivity
import com.prestosoftware.test.rappi.util.compose.ViewModelFragment
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
