package ru.gmasalskikh.ezcs.providers.data_repository

import ru.gmasalskikh.ezcs.data.type.EntityType

interface DataRepository {
    suspend fun <T> getListEntities(entityType: EntityType, clazz: Class<T>): List<T>
    suspend fun <T> getEntity(entityType: EntityType, entityName: String, clazz: Class<T>): T
}