package com.mathieukh.tutopsychop13.data.local

import com.mathieukh.tutopsychop13.data.CallbackRepository
import com.mathieukh.tutopsychop13.data.DataSource
import com.mathieukh.tutopsychop13.data.entities.News

object LocalDataSource : DataSource {

    override fun getLastNews(byRange: Int, callback: CallbackRepository<List<News>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}