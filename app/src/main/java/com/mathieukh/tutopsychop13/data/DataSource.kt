package com.mathieukh.tutopsychop13.data

interface DataSource {
    fun getLastNews(byRange: Int, callback: CallbackRepository)
}

interface CallbackRepository {
    fun onSuccess(data: Any)
    fun onError(message: String)
}