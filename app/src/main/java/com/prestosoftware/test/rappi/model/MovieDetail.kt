package com.prestosoftware.test.rappi.model

import java.util.*

data class MovieDetail(val id: Long, val title: String, val posterPath: String,
                       val backdropPath: String?, val overview: String,
                       val releaseDate: Date?,
                       val tagline: String, val voteAverage: Float, val voteCount: Int,
                       val status: String, val genres: List<Genre>)