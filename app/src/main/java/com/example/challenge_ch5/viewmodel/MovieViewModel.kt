package com.example.challenge_ch5.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.challenge_ch5.MyDataStore
import com.example.challenge_ch5.helper.Resource
import com.example.challenge_ch5.model.MovieResponse
import com.example.challenge_ch5.model.MovieDetail
import com.example.challenge_ch5.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repository: MovieRepository, private val dataStore: MyDataStore) : ViewModel() {

    val moviesLiveData = MutableLiveData<List<MovieResponse>>()



    fun fetchMoviePlayingNow() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = repository.fetchMoviePlayingNow()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun fetchMovieTopRated() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = repository.fetchMovieTopRated()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getDetailMovie(movieId : Int) : LiveData<Resource<MovieDetail>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = repository.getMovieDetail(movieId)))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }


//    fun fetchNowPlayingMovies() {
//        ApiClient.instance.getMovieNowPlaying(ApiKey.apiBearer).enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                if (response.isSuccessful) {
//                    val movieResponse = response.body()
//                    if (movieResponse != null) {
//                        moviesLiveData.value = movieResponse.results
//                    } else {
//                        errorLiveData.value = "Movie response body is null"
//                    }
//                } else {
//                    errorLiveData.value = "Failed to get movie data: ${response.code()}"
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                errorLiveData.value = "Failed to get movie data: ${t.message}"
//            }
//        })
//    }
}
