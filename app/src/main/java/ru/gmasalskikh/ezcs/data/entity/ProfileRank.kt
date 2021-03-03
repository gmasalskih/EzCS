package ru.gmasalskikh.ezcs.data.entity

import ru.gmasalskikh.ezcs.data.type.EntityType

data class ProfileRank(
    override val name: String = "",
    override val entityType: EntityType = EntityType.PROFILE_RANK,
    val xp: Int = 0,
    val logo: String = "",
    val order: Int = 0
) : Entity