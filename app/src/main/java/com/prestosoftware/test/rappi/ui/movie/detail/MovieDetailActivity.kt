package com.prestosoftware.test.rappi.ui.movie.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prestosoftware.test.R
import com.prestosoftware.test.rappi.data.api.Api
import com.prestosoftware.test.databinding.ActivityMovieDetailBinding
import com.prestosoftware.test.rappi.models.Video
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.ui.movie.VideoListAdapter
import com.prestosoftware.test.rappi.ui.movie.VideoListViewHolder
import com.prestosoftware.test.rappi.util.compose.ViewModelActivity
import com.prestosoftware.test.rappi.util.extension.applyToolbarMargin
import com.prestosoftware.test.rappi.util.extension.simpleToolbarWithHome
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_movie_detail_body.*
import org.jetbrains.anko.startActivity

class MovieDetailActivity : ViewModelActivity(), VideoListViewHolder.Delegate {

    private val vm by viewModel<MovieDetailViewModel>()
    private val binding by binding<ActivityMovieDetailBinding>(R.layout.activity_movie_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.postMovieId(getMovieFromIntent().id)
        with(binding) {
            lifecycleOwner = this@MovieDetailActivity
            viewModel = vm
            detailBody.viewModel = vm
            movie = getMovieFromIntent()
            detailHeader.movie = getMovieFromIntent()
            detailBody.movie = getMovieFromIntent()
        }
        initializeUI()
    }

    private fun initializeUI() {
        applyToolbarMargin(movie_detail_toolbar)
        simpleToolbarWithHome(movie_detail_toolbar, getMovieFromIntent().title)
        detail_body_recyclerView_trailers.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        detail_body_recyclerView_trailers.adapter = VideoListAdapter(this)
    }

    private fun getMovieFromIntent(): Movie {
        return intent.getParcelableExtra(movieId) as Movie
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

    override fun onItemClicked(video: Video) {
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Api.getYoutubeVideoPath(video.key)))
        startActivity(playVideoIntent)
    }

    companion object {
        private const val movieId = "movie"
        fun startActivityModel(context: Context?, movie: Movie) {
            context?.startActivity<MovieDetailActivity>(movieId to movie)
        }
    }
}
