package com.mathieukh.tutopsychop13.data

import com.mathieukh.tutopsychop13.data.entities.News

interface DataSource {
    fun getLastNews(byRange: Int, callback: CallbackRepository<List<News>>)
}

interface CallbackRepository<in T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}