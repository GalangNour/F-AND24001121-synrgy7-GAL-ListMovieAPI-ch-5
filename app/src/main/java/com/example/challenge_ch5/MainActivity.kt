package com.example.challenge_ch5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        ApiClient.instance.getMovieNowPlaying(ApiKey.apiBearer).enqueue(object : Callback<MovieResponse>{
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                Log.e("TES", Gson().toJson(response.body()))
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                Log.e("TES", Gson().toJson(t.message))
//            }
//
//
//        })
    }
}