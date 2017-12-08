package com.mathieukh.tutopsychop13.data.remote

import com.google.gson.GsonBuilder
import com.mathieukh.tutopsychop13.data.CallbackRepository
import com.mathieukh.tutopsychop13.data.DataSource
import com.mathieukh.tutopsychop13.data.deserializers.NewsDeserializer
import com.mathieukh.tutopsychop13.data.entities.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource : DataSource {

    private const val FB_API_VERSION = "v2.11"
    private const val FB_API = "https://graph.facebook.com/$FB_API_VERSION/"

    private val facebookAPI: FacebookService

    init {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(List::class.java, NewsDeserializer())

        val retrofit = Retrofit.Builder()
                .baseUrl(FB_API)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()

        facebookAPI = retrofit.create(FacebookService::class.java)
    }

    override fun getLastNews(byRange: Int, callback: CallbackRepository) {
        facebookAPI.getLastNews(byRange).enqueue(object : Callback<List<News>> {

            override fun onFailure(call: Call<List<News>>?, t: Throwable) {
                callback.onError(t.message ?: "")
            }

            override fun onResponse(call: Call<List<News>>?, response: Response<List<News>>?) {
                response?.let {
                    if (it.isSuccessful) {
                        callback.onSuccess(it.body() ?: listOf<News>())
                    }
                }
            }
        })
    }
}