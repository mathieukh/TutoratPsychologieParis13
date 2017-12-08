package com.mathieukh.tutopsychop13.data

import com.mathieukh.tutopsychop13.data.entities.News
import com.mathieukh.tutopsychop13.data.local.LocalDataSource
import com.mathieukh.tutopsychop13.data.remote.RemoteDataSource

object Repository : DataSource {

    private val local: DataSource = LocalDataSource
    private val remote: DataSource = RemoteDataSource

    override fun getLastNews(byRange: Int, callback: CallbackRepository<List<News>>) {
        remote.getLastNews(byRange, object : CallbackRepository<List<News>> {
            override fun onSuccess(data: List<News>) {
                callback.onSuccess(data)
            }

            override fun onError(message: String) {
                callback.onError(message)
            }

        })
    }
}