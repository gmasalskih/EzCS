package ru.gmasalskikh.ezcs.data.firestore_entities

import ru.gmasalskikh.ezcs.data.type.EntityType

data class CompetitiveFirestoreEntity(
    override val name: String = "",
    override val entityType: EntityType = EntityType.COMPETITIVE,
    val logo: String = "",
    val order: Int = 0
) : FirestoreEntity