package com.example.challenge_ch5.repository

import com.example.challenge_ch5.helper.RemoteDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun fetchMoviePlayingNow() = remoteDataSource.fetchMoviePlayingNow()

    suspend fun getMovieDetail(movieId : Int) = remoteDataSource.getDetailMovie(movieId)

    suspend fun fetchMovieTopRated() = remoteDataSource.fetchMovieTopRated()

}
