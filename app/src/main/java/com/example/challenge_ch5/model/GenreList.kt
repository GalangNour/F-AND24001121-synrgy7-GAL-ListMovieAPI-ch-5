package com.example.challenge_ch5.model


import com.google.gson.annotations.SerializedName

data class GenreList(
    @SerializedName("genres")
    val genres: List<Genre>
)