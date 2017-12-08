package com.mathieukh.tutopsychop13.data.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mathieukh.tutopsychop13.data.entities.News
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class NewsDeserializer : JsonDeserializer<List<News>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<News> {
        json?.let {
            it.asJsonObject.let { jsonObject ->
                return jsonObject.getAsJsonArray("data").map { element ->
                    val id = element.asJsonObject.getAsJsonPrimitive("id").asString
                    val incomingFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
                    val createdTime = incomingFormat.parse(element.asJsonObject.getAsJsonPrimitive("created_time").asString)
                    val message = if (element.asJsonObject.has("message")) element.asJsonObject.getAsJsonPrimitive("message").asString else ""
                    News(id, createdTime, message, "")
                }
            }
        }
        throw Exception("Impossible de récupérer la réponse du serveur")
    }

}
