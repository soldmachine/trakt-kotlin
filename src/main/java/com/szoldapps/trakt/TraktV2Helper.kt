package com.szoldapps.trakt

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.szoldapps.trakt.enums.*
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime

object TraktV2Helper {

    @JvmStatic
    val gsonBuilder: GsonBuilder
        get() {
            val builder = GsonBuilder()

            // trakt exclusively uses ISO 8601 date times with milliseconds and time zone offset
            // such as '2011-12-03T10:15:30.000+01:00' or '2011-12-03T10:15:30.000Z'
            builder.registerTypeAdapter(
                OffsetDateTime::class.java,
                JsonDeserializer<OffsetDateTime> { json, _, _ -> OffsetDateTime.parse(json.asString) }
            )
            builder.registerTypeAdapter(
                OffsetDateTime::class.java,
                JsonSerializer<OffsetDateTime> { src, _, _ -> JsonPrimitive(src.toString()) }
            )

            // dates are in ISO 8601 format as well
            builder.registerTypeAdapter(
                LocalDate::class.java,
                JsonDeserializer<LocalDate> { json, _, _ -> LocalDate.parse(json.asString) }
            )

            // privacy
            builder.registerTypeAdapter(
                ListPrivacy::class.java,
                JsonDeserializer<ListPrivacy> { json, _, _ -> ListPrivacy.fromValue(json.asString) }
            )

            // rating
            builder.registerTypeAdapter(
                Rating::class.java,
                JsonDeserializer<Rating> { json, _, _ -> Rating.fromValue(json.asInt) }
            )
            builder.registerTypeAdapter(
                Rating::class.java,
                JsonSerializer<Rating> { src, _, _ -> JsonPrimitive(src.value) }
            )

            // sort by
            builder.registerTypeAdapter(
                SortBy::class.java,
                JsonDeserializer<SortBy> { json, _, _ -> SortBy.fromValue(json.asString) }
            )
            // sort how
            builder.registerTypeAdapter(
                SortHow::class.java,
                JsonDeserializer<SortHow> { json, _, _ -> SortHow.fromValue(json.asString) }
            )

            // status
            builder.registerTypeAdapter(
                Status::class.java,
                JsonDeserializer<Status> { json, _, _ -> Status.fromValue(json.asString) }
            )

            return builder
        }

}
