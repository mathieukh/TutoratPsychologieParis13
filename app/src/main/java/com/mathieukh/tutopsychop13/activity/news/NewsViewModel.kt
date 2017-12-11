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

    // On instancie le repository (définit comme un singleton)
    private val repository = Repository
    // On définit une valeur qui permet de réclamer les X dernières news (Pourra être exporter plus tard pour être modifier dans des préférences)
    private val rangeNews = 10

    // Données du ViewModel. Liste de news
    var mNews: MutableLiveData<List<News>> = MutableLiveData()
    // Données du ViewModel. Booléen de chargement
    var mLoading: MutableLiveData<Boolean> = MutableLiveData()

    // Fonction d'initialisation
    init {
        // On initialise la liste vide des news tout d'abord. (Voir si ça ne peut pas être retiré)
        mNews.value = listOf()
        // On rafraîchit les news à l'initialisation
        refreshNews()
    }

    // Fonction de rafraîssement des news
    fun refreshNews() {
        // On définit le loading comme vrai pour que la vue puisse afficher un état de chargement
        mLoading.value = true
        // On va aller requeter le repository pour aller chercher les X dernières nouvelles
        repository.getLastNews(rangeNews, object : CallbackRepository<List<News>> {
            override fun onSuccess(data: List<News>) {
                // En cas de succes de notre requete, on remplace la valeur des news par les données reçues
                mNews.value = data
                // On signale que le chargement des données est fini
                mLoading.value = false
            }

            override fun onError(message: String) {
                // En cas d'échec, on signale seulement que le chargement des données est fini. Les news restent inchangées
                mLoading.value = false
            }

        })
    }

}
