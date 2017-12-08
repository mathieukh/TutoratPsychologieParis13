package com.mathieukh.tutopsychop13.activity.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mathieukh.tutopsychop13.data.CallbackRepository
import com.mathieukh.tutopsychop13.data.Repository
import com.mathieukh.tutopsychop13.data.entities.News

/*
* Classe permettant de modéliser le ViewModel des news
 */
class NewsViewModel : ViewModel() {

    private val repository = Repository
    private val rangeNews = 10

    // Données du ViewModel. Liste de news
    var mNews: MutableLiveData<List<News>> = MutableLiveData()
    var mLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        mNews.value = listOf()
        mLoading.value = true
        refreshNews()
    }

    fun refreshNews() {
        repository.getLastNews(rangeNews, object : CallbackRepository {
            override fun onSuccess(data: Any) {
                mNews.value = data as List<News>
                mLoading.value = false
            }

            override fun onError(message: String) {
                mLoading.value = false
            }

        })
    }

}
