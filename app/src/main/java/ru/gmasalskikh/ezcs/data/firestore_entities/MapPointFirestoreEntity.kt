package ru.gmasalskikh.ezcs.data.firestore_entities

import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.data.type.GrenadeType
import ru.gmasalskikh.ezcs.data.type.TickrateType
import ru.gmasalskikh.ezcs.utils.toValidId
import java.lang.StringBuilder
import java.util.*

data class MapPointFirestoreEntity(
    override val entityType: EntityType = EntityType.MAP_POINT,
    override val name: String = "",
    val mapDocumentName: String = "",
    val grenadeType: GrenadeType = GrenadeType.SMOKE,
    val tickrateTypes: List<TickrateType> = listOf(),
    val previewStart: String = "",
    val previewEnd: String = "",
    val contentImages: List<String> = listOf(),
    val contentVideos: List<String> = listOf(),
) : FirestoreEntity {
    override fun getDocumentName(): String = StringBuilder().apply {
        append(entityType.name)
        append("/")
        append(mapDocumentName.substringAfterLast("/").toLowerCase(Locale.getDefault()))
        append("_")
        append(grenadeType.name.toLowerCase(Locale.getDefault()))
        append("_")
        tickrateTypes.forEach {
            append(it.value)
            append("_")
        }
        append(name.toValidId())
    }.toString()

    fun getFairName(): String = StringBuilder().apply {
        append(mapDocumentName.substringAfterLast("/").toLowerCase(Locale.getDefault()))
        append("_")
        append(grenadeType.name.toLowerCase(Locale.getDefault()))
        append("_")
        tickrateTypes.forEach {
            append(it.value)
            append("_")
        }
        append(name.toValidId())
    }.toString()

}