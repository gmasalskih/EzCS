package ru.gmasalskikh.ezcs.data.entity

import ru.gmasalskikh.ezcs.data.type.EntityType

data class DangerZone(
    override val name: String = "",
    override val entityType: EntityType = EntityType.DANGER_ZONE,
    val logo: String = "",
    val order: Int = 0
) : Entity