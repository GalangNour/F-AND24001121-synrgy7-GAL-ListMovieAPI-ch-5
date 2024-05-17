package com.example.challenge_ch5

import com.example.challenge_ch5.model.MovieResponse
import com.example.challenge_ch5.model.MovieDetail
import retrofit2.http.*

interface ApiService {

    // using api key
//    @GET("movie/now_playing")
//    fun getMovieNowPlaying(@Query("api_key") apiKey: String): Call<MovieResponse>


    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(@Header("Authorization") apiBearer : String = ApiKey.apiBearer) : MovieResponse

    @GET("movie/top_rated")
    suspend fun getMovieTopRated(@Header("Authorization") apiBearer : String = ApiKey.apiBearer) : MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Header("Authorization") apiBearer : String = ApiKey.apiBearer,
        @Path("movie_id") movieId : Int
    ) : MovieDetail

//    @GET("genre/movie/list")
//    fun getGenreList(@Header("Authorization") apiBearer : String): Call<>)



}