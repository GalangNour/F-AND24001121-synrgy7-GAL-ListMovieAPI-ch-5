package com.example.challenge_ch5.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.challenge_ch5.MyDataStore

class UserViewModel(private val pref : MyDataStore) : ViewModel() {



    fun registerUser(username: String, password: String, email: String) {
        viewModelScope.launch {
            pref.saveUser(username, password, email)
        }
    }

    fun getUsername(): LiveData<String>{
        return pref.getUsername().asLiveData()
    }

    fun getEmail(): LiveData<String>{
        return pref.getEmail().asLiveData()
    }

    fun getPassword(): LiveData<String>{
        return pref.getPassword().asLiveData()
    }

    fun setUserLoggedIn(isUserLoggedIn: Boolean){
        viewModelScope.launch {
            pref.setUserLoggedIn(isUserLoggedIn)
        }
    }

    fun isUserLoggedIn(): LiveData<Boolean> {
        return pref.isUserLoggedIn().asLiveData()
    }
}
