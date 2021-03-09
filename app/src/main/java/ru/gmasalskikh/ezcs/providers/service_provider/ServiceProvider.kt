package ru.gmasalskikh.ezcs.providers.service_provider

import ru.gmasalskikh.ezcs.data.type.EntityType

interface ServiceProvider {
    val mapper: Mapper

    suspend fun <SOURCE, TARGET> getEntityList(
        entityType: EntityType,
        clazz: Class<SOURCE>,
        mapper: suspend (SOURCE) -> TARGET
    ): List<TARGET>

    suspend fun <SOURCE, TARGET> getEntity(
        entityType: EntityType,
        entityName: String,
        clazz: Class<SOURCE>,
        mapper: suspend (SOURCE) -> TARGET
    ): TARGET
}