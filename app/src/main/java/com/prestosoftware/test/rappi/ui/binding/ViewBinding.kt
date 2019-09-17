package com.prestosoftware.test.rappi.ui.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import co.lujun.androidtagview.TagContainerLayout
import com.bumptech.glide.Glide
import com.prestosoftware.test.rappi.data.api.Api
import com.prestosoftware.test.rappi.models.Keyword
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.util.KeywordListMapper
import com.prestosoftware.test.rappi.util.extension.bindResource
import com.prestosoftware.test.rappi.util.extension.requestGlideListener
import com.prestosoftware.test.rappi.util.extension.visible

@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<List<Any>>?) {
  view.bindResource(resource) {
    if (resource?.data?.isNotEmpty()!!) {
      view.visible()
    }
  }
}

@BindingAdapter("mapKeywordList")
fun bindMapKeywordList(view: TagContainerLayout, resource: Resource<List<Keyword>>?) {
  view.bindResource(resource) {
    view.tags = KeywordListMapper.mapToStringList(resource?.data!!)
    if (resource.data.isNotEmpty()) {
      view.visible()
    }
  }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindReleaseDate")
fun bindReleaseDate(view: TextView, movie: Movie) {
  view.text = "Release Date : ${movie.release_date}"
}

//@SuppressLint("SetTextI18n")
//@BindingAdapter("bindAirDate")
//fun bindAirDate(view: TextView, tv: Tv) {
//  view.text = "First Air Date : ${tv.first_air_date}"
//}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, movie: Movie) {
  if (movie.backdrop_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(movie.backdrop_path))
      .listener(view.requestGlideListener())
      .into(view)
  } else {
    Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path!!))
      .listener(view.requestGlideListener())
      .into(view)
  }
}

//@BindingAdapter("bindBackDrop")
//fun bindBackDrop(view: ImageView, tv: Tv) {
//  if (tv.backdrop_path != null) {
//    Glide.with(view.context).load(Api.getBackdropPath(tv.backdrop_path))
//      .listener(view.requestGlideListener())
//      .into(view)
//  } else {
//    Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
//      .listener(view.requestGlideListener())
//      .into(view)
//  }
//}

//@BindingAdapter("bindBackDrop")
//fun bindBackDrop(view: ImageView, person: Person) {
//  if (person.profile_path != null) {
//    Glide.with(view.context).load(Api.getBackdropPath(person.profile_path))
//      .apply(RequestOptions().circleCrop())
//      .into(view)
//  }
//}
