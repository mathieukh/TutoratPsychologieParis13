package com.mathieukh.tutopsychop13.data

import com.mathieukh.tutopsychop13.data.local.LocalDataSource
import com.mathieukh.tutopsychop13.data.remote.RemoteDataSource

object Repository : DataSource {

    private val local: DataSource = LocalDataSource
    private val remote: DataSource = RemoteDataSource

    override fun getLastNews(byRange: Int, callback: CallbackRepository) {
        remote.getLastNews(byRange, object : CallbackRepository {
            override fun onSuccess(data: Any) {
                callback.onSuccess(data)
            }

            override fun onError(message: String) {
                callback.onError(message)
            }

        })
    }
}