// Generated by Dagger (https://dagger.dev).
package com.prestosoftware.test.reign.ui.post;

import com.prestosoftware.test.reign.repository.PostRepository;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PostViewModel_Factory implements Factory<PostViewModel> {
  private final Provider<PostRepository> postRepositoryProvider;

  public PostViewModel_Factory(Provider<PostRepository> postRepositoryProvider) {
    this.postRepositoryProvider = postRepositoryProvider;
  }

  @Override
  public PostViewModel get() {
    return new PostViewModel(postRepositoryProvider.get());
  }

  public static PostViewModel_Factory create(Provider<PostRepository> postRepositoryProvider) {
    return new PostViewModel_Factory(postRepositoryProvider);
  }

  public static PostViewModel newInstance(PostRepository postRepository) {
    return new PostViewModel(postRepository);
  }
}