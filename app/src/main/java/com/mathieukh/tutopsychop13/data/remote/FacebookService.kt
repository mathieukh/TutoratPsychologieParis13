package com.mathieukh.tutopsychop13.data.remote

import com.mathieukh.tutopsychop13.data.entities.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val FB_PAGE_ID = "997342000330131"
const val ACCESS_TOKEN = "1623771404375571|OJuXO8Goys-V9-VckQlYc6h6-I4"

interface FacebookService {

    @GET("$FB_PAGE_ID/posts")
    fun getLastNews(@Query("limit") limit: Int, @Query("access_token") accessToken: String = ACCESS_TOKEN): Call<List<News>>

    @GET(value = "{POST_ID}/attachments")
    fun getPostAttachment(@Path("POST_ID") postId: String, @Query("access_token") accessToken: String = ACCESS_TOKEN): Call<String>

}
