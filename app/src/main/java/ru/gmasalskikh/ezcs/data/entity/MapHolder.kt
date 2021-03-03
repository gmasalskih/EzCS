package ru.gmasalskikh.ezcs.data.entity

import ru.gmasalskikh.ezcs.data.type.EntityType

data class MapHolder(
    override val name: String = "",
    override val entityType: EntityType = EntityType.MAP_HOLDER,
    @field:JvmField
    val isCompetitive: Boolean = true,
    val logo: String = "",
    val map: String = "",
    val wallpaper: String = "",
) : Entity