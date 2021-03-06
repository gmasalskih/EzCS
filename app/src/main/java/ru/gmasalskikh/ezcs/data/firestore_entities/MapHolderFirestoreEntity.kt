package ru.gmasalskikh.ezcs.data.firestore_entities

import ru.gmasalskikh.ezcs.data.type.EntityType

data class MapHolderFirestoreEntity(
    override val name: String = "",
    override val entityType: EntityType = EntityType.MAP_HOLDER,
    @field:JvmField
    val isCompetitive: Boolean = true,
    val logo: String = "",
    val map: String = "",
    val wallpaper: String = "",
) : FirestoreEntity