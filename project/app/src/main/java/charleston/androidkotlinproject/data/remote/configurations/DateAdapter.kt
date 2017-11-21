package charleston.androidkotlinproject.data.remote.configurations

import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type
import java.util.*


/**
 * Created by charleston on 03/10/17.
 */
class DateAdapter : JsonDeserializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
        var dateStr = json.asString
        if (dateStr != null) dateStr = dateStr.replace("/Date(", "")
        if (dateStr != null) dateStr = dateStr.replace("-0000)/", "")
        if (dateStr != null) dateStr = dateStr.replace(")/", "")
        val time = java.lang.Long.parseLong(dateStr)
        return Date(time)
    }
}