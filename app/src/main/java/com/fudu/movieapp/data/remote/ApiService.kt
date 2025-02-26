package com.fudu.movieapp.data.remote

import com.fudu.movieapp.data.response.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("https://www.omdbapi.com/")
    fun getSearchMovie(
        @Query("s") movieName: String,
        @Query("apikey") apikey: String = "8d6aa4ca"
    ): Call<MovieResponse>
}