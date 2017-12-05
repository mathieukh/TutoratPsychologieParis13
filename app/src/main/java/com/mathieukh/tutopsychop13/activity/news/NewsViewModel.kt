package com.mathieukh.tutopsychop13.activity.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mathieukh.tutopsychop13.data.News
import java.util.*

class NewsViewModel : ViewModel() {

    var mNews: MutableLiveData<List<News>> = MutableLiveData()

    init {
        mNews.value = listOf(News(Date(), "Bonjour à tous"), News(Date(), "Au revoir à tous"))
    }

}
