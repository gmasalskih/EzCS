package ru.gmasalskikh.ezcs.providers.service_provider

import kotlinx.coroutines.*
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.data_repository.DataRepository

class ServiceProviderImpl(
    private val dataRepository: DataRepository,
    override val mapper: Mapper
) : ServiceProvider {

    override suspend fun <SOURCE, TARGET> getEntityList(
        entityType: EntityType,
        clazz: Class<SOURCE>,
        mapper: suspend (SOURCE) -> TARGET
    ): List<TARGET> = withContext(Dispatchers.IO) {
        dataRepository.getListEntities(
            entityType = entityType,
            clazz = clazz
        ).map { mapper(it) }.toList()
    }

    override suspend fun <SOURCE, TARGET> getEntity(
        entityType: EntityType,
        entityName: String,
        clazz: Class<SOURCE>,
        mapper: suspend (SOURCE) -> TARGET
    ): TARGET = withContext(Dispatchers.IO) {
        dataRepository.getEntity(
            entityType = entityType,
            entityName = entityName,
            clazz = clazz
        ).let { mapper(it) }
    }
}