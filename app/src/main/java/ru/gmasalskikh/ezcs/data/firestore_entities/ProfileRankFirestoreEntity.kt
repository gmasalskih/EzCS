package ru.gmasalskikh.ezcs.data.firestore_entities

import ru.gmasalskikh.ezcs.data.type.EntityType

data class ProfileRankFirestoreEntity(
    override val name: String = "",
    override val entityType: EntityType = EntityType.PROFILE_RANK,
    val xp: Int = 0,
    val logo: String = "",
    val order: Int = 0
) : FirestoreEntity