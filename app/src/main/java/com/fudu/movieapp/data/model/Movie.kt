package com.fudu.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val releaseYear: String,
    @SerializedName("Poster")
    val posterImgUrl: String
)
