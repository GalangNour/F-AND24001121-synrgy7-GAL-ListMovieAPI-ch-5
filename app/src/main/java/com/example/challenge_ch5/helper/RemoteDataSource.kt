package com.example.challenge_ch5.helper

import com.example.challenge_ch5.ApiService

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun fetchMoviePlayingNow() = apiService.getMovieNowPlaying()

    suspend fun getDetailMovie(movieId : Int) = apiService.getMovieDetail(movieId = movieId)

    suspend fun fetchMovieTopRated() = apiService.getMovieTopRated()
}