package com.mathieukh.tutopsychop13.data.remote

import com.google.gson.GsonBuilder
import com.mathieukh.tutopsychop13.data.CallbackRepository
import com.mathieukh.tutopsychop13.data.DataSource
import com.mathieukh.tutopsychop13.data.deserializers.NewsDeserializer
import com.mathieukh.tutopsychop13.data.entities.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* Classe permettant de représenter la source remote des données
* Utilisé le Repository
 */
object RemoteDataSource : DataSource {

    // Variable constante de la version de l'API Facebook utilisée
    private const val FB_API_VERSION = "v2.11"
    // Variable constante de l'URL de l'API Facebook
    private const val FB_API = "https://graph.facebook.com/$FB_API_VERSION/"
    // Variable permettant de stocker le service Facebook
    private val facebookAPI: FacebookService

    // Fonction d'initialisation
    init {
        // On construit notre parser json qui permettra pour l'instant de desérialiser les news
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(List::class.java, NewsDeserializer())

        // On crée notre service API
        facebookAPI = Retrofit.Builder()
                .baseUrl(FB_API)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
                .create(FacebookService::class.java)
    }

    // Fonction permettant de récupérer les dernières nouvelles
    override fun getLastNews(byRange: Int, callback: CallbackRepository<List<News>>) {
        facebookAPI.getLastNews(byRange).enqueue(object : Callback<List<News>> {

            override fun onFailure(call: Call<List<News>>?, t: Throwable) {
                callback.onError(t.message ?: "")
            }

            override fun onResponse(call: Call<List<News>>?, response: Response<List<News>>?) {
                response?.let {
                    if (it.isSuccessful) {
                        callback.onSuccess(it.body() ?: listOf<News>())
                    }
                }
            }
        })
    }
}