package com.mathieukh.tutopsychop13.data

import com.mathieukh.tutopsychop13.data.entities.News

/*
* Interface permettant de stocker les fonctions que toutes les DataSources devront fournir
 */
interface DataSource {
    fun getLastNews(byRange: Int, callback: CallbackRepository<List<News>>)
}

/*
* Interface de callback permettant de remonter les données et les erreurs entre les DataSources.
* Elle est généralisé par T pour permettre de retourner des données différentes selon les fonctions
 */
interface CallbackRepository<in T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}