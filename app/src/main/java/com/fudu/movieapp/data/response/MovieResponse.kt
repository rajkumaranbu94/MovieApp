package com.fudu.movieapp.data.response

import com.fudu.movieapp.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search")
    val search: List<Movie>,
    val totalResults: String
)