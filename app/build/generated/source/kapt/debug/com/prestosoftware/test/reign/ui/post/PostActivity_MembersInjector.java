// Generated by Dagger (https://dagger.dev).
package com.prestosoftware.test.reign.ui.post;

import androidx.lifecycle.ViewModelProvider;
import com.prestosoftware.test.rappi.util.compose.ViewModelActivity_MembersInjector;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PostActivity_MembersInjector implements MembersInjector<PostActivity> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PostActivity_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PostActivity> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PostActivity_MembersInjector(viewModelFactoryProvider);}

  @Override
  public void injectMembers(PostActivity instance) {
    ViewModelActivity_MembersInjector.injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }
}