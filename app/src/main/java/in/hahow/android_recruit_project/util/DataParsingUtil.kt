@file:Suppress("unused")

package `in`.hahow.android_recruit_project.util

import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.*

/**
 * Serialization: united util extension functions.
 *
 * Credit: Samuel.T.Chou since 2022.07
 */

val JSON_SERIALIZER = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

inline fun <reified T> String.jsonToObject(): T = JSON_SERIALIZER.decodeFromString(this)
fun <T> String.jsonToObject(
    deserializationStrategy: DeserializationStrategy<T>,
): T = JSON_SERIALIZER.decodeFromString(deserializationStrategy, this)

fun <T> JsonElement.jsonToObject(
    deserializationStrategy: DeserializationStrategy<T>,
) = JSON_SERIALIZER.decodeFromJsonElement(deserializationStrategy, this)

fun <T> JsonObject.jsonToObject(
    deserializationStrategy: DeserializationStrategy<T>,
) = JSON_SERIALIZER.decodeFromJsonElement(deserializationStrategy, this)

/**
 * transform to Map<String, T>.
 */
fun <T> JsonElement.toStringMap(serializer: KSerializer<T>) =
    JSON_SERIALIZER.decodeFromJsonElement(MapSerializer(String.serializer(), serializer), this)

fun <K, V> JsonElement.toMapOf(ks: KSerializer<K>, vs: KSerializer<V>) =
    JSON_SERIALIZER.decodeFromJsonElement(MapSerializer(ks, vs), this)

fun <T> JsonElement.toList(serializer: KSerializer<T>) =
    JSON_SERIALIZER.decodeFromJsonElement(ListSerializer(serializer), this)

inline fun <reified T> T.encodeToJsonString() = JSON_SERIALIZER.encodeToString(this)
fun <T> T.encodeToJsonString(
    serializationStrategy: SerializationStrategy<T>,
) = JSON_SERIALIZER.encodeToString(serializationStrategy, this)

fun JsonObject.getNullable(key: String) = get(key).let { if (it != JsonNull) it else null }

fun JsonObject.getAsJsonObject(key: String) = get(key)?.jsonObject
fun JsonObject.getAsJsonArray(key: String) = get(key)?.jsonArray

fun JsonElement.asString(): String = this.jsonPrimitive.content
fun JsonElement.asBoolean(): Boolean = this.jsonPrimitive.boolean
fun JsonElement.asInt(): Int = this.jsonPrimitive.int
fun JsonElement.asLong(): Long = this.jsonPrimitive.long
fun JsonElement.asFloat(): Float = this.jsonPrimitive.float
fun JsonElement.asDouble(): Double = this.jsonPrimitive.double
