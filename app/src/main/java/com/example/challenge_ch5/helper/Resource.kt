package com.example.challenge_ch5.helper

import androidx.lifecycle.LiveData

enum class Status{
    SUCESSS,
    ERROR,
    LOADING
}

data class Resource<out T>(val status : Status, val data : T?, val message : String?) {
    companion object{
        fun <T> success(data: T?) : Resource<T> = Resource(status = Status.SUCESSS, data = data, message = null)

        fun <T>error(data: T?, message: String?) :Resource<T> =
            Resource(status = Status.ERROR, data =data, message = null)

        fun <T>loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}