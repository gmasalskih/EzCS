package ru.gmasalskikh.ezcs.data.firestore_entities

import ru.gmasalskikh.ezcs.data.type.EntityType

data class WingmanFirestoreEntity(
    override val name: String = "",
    override val entityType: EntityType = EntityType.WINGMAN,
    val logo: String = "",
    val order: Int = 0
) : FirestoreEntity