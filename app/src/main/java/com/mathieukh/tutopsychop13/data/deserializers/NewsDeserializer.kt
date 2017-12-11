package com.mathieukh.tutopsychop13.data.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mathieukh.tutopsychop13.data.entities.News
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/*
* Classe qui permet de désérialiser les news reçues depuis l'API Facebook vers l'objet News définie dans l'application
 */
class NewsDeserializer : JsonDeserializer<List<News>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<News> {
        // Si l'objet json n'existe pas, on soulève une erreur
        json?.let {
            // On converti le jsonElement en JsonObject
            it.asJsonObject.let { jsonObject ->
                // On récupère le jsonArray data que l'on va transformer en liste de news que l'on retournera
                return jsonObject.getAsJsonArray("data").map { element ->
                    // On récupère l'id de l'objet
                    val id = element.asJsonObject.getAsJsonPrimitive("id").asString
                    // On définit le pattern de la date
                    val incomingFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
                    // On parse la date selon le pattern
                    val createdTime = incomingFormat.parse(element.asJsonObject.getAsJsonPrimitive("created_time").asString)
                    // On récupère le message
                    val message = if (element.asJsonObject.has("message")) element.asJsonObject.getAsJsonPrimitive("message").asString else ""
                    // On ne définit la l'url image de la news car elle doit être récupéré depuis une autre requête API
                    News(id, createdTime, message, "")
                }
            }
        }
        // On renvoie une erreur
        throw Exception("Impossible de récupérer la réponse du serveur")
    }

}
