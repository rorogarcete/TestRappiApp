package com.prestosoftware.test.rappi.room

import androidx.test.runner.AndroidJUnit4
import com.prestosoftware.test.rappi.models.entity.Movie
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import com.prestosoftware.test.rappi.helpers.MockTestUtil
import com.prestosoftware.test.rappi.helpers.LiveDataTestUtil

@RunWith(AndroidJUnit4::class)
class MovieDaoTest : MovieDbTest() {

  @Test
  fun insertAndReadTest() {
    val movieList = ArrayList<Movie>()
    val movie = MockTestUtil.mockMovie()
    movieList.add(movie)

    db.movieDao().insertAll(movieList)
    val loadFromDB = LiveDataTestUtil.getValue(db.movieDao().getMovieList(movie.page))[0]
    assertThat(loadFromDB.page, `is`(1))
    assertThat(loadFromDB.id, `is`(123))
  }

  @Test
  fun updateAndReadTest() {
    val movieList = ArrayList<Movie>()
    val movie = MockTestUtil.mockMovie()
    movieList.add(movie)
    db.movieDao().insertAll(movieList)

    val loadFromDB = db.movieDao().getMovie(movie.id)
    assertThat(loadFromDB.page, `is`(1))

    movie.page = 10
    db.movieDao().updateMovie(movie)

    val updated = db.movieDao().getMovie(movie.id)
    assertThat(updated.page, `is`(10))
  }
}
