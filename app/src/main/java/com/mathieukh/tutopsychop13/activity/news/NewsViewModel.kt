package com.mathieukh.tutopsychop13.activity.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mathieukh.tutopsychop13.data.News
import java.util.*

/*
* Classe permettant de modéliser le ViewModel des news
 */
class NewsViewModel : ViewModel() {

    // Données du ViewModel. Liste de news
    var mNews: MutableLiveData<List<News>> = MutableLiveData()

    // On instancie temporairement des fausses données
    init {
        mNews.value = listOf(News("1", Date(), "Bonjour à tous"), News("2", Date(), "Au revoir à tous"))
    }

}
