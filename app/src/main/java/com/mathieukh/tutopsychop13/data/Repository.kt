package com.mathieukh.tutopsychop13.data

import com.mathieukh.tutopsychop13.data.entities.News
import com.mathieukh.tutopsychop13.data.local.LocalDataSource
import com.mathieukh.tutopsychop13.data.remote.RemoteDataSource

/*
* Classe permettant de faire la relation entre les différents DataSources.
* Elle est la seule DataSource que l'application doit utilisée. Elle se charge de faire l'aggrégation et la synchronisation
 */
object Repository : DataSource {
    // Variable stockant la DataSource local
    private val local: DataSource = LocalDataSource
    // Variable stockant la DataSource remote
    private val remote: DataSource = RemoteDataSource

    // Fonction permettant de récupérer les données depuis l'API facebook
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