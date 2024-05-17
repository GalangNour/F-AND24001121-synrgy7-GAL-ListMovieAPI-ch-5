package com.example.challenge_ch5.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_ch5.ApiClient
import com.example.challenge_ch5.MyDataStore
import com.example.challenge_ch5.helper.RemoteDataSource
import com.example.challenge_ch5.repository.MovieRepository


class ViewModelFactory(private val remoteDataSource: RemoteDataSource, private val dataStore: MyDataStore) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    RemoteDataSource(ApiClient.instance),
                    MyDataStore(context)

                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(MovieRepository(remoteDataSource), dataStore) as T
            }
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(dataStore) as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}
