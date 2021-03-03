package ru.gmasalskikh.ezcs.data.entity

import ru.gmasalskikh.ezcs.data.type.EntityType

data class Wingman(
    override val name: String = "",
    override val entityType: EntityType = EntityType.WINGMAN,
    val logo: String = "",
    val order: Int = 0
) : Entity