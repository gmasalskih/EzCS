package ru.gmasalskikh.ezcs.providers.service_provider

import kotlinx.coroutines.async
import kotlinx.coroutines.job
import ru.gmasalskikh.ezcs.data.firestore_entities.CompetitiveFirestoreEntity
import ru.gmasalskikh.ezcs.data.pojo.Competitive
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.content_repository.ContentRepository
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.data_repository.DataRepository
import kotlin.coroutines.coroutineContext

class ServiceProviderImpl(
    private val dataRepository: DataRepository,
    private val contentRepository: ContentRepository,
    private val cs: CustomCoroutineScope
) : ServiceProvider {

    override suspend fun getCompetitiveList(): List<Competitive> {
        return dataRepository.getListEntities(
            EntityType.COMPETITIVE,
            CompetitiveFirestoreEntity::class.java
        ).map { competitiveFirestoreEntity ->
            Competitive(
                name = competitiveFirestoreEntity.name,
                order = competitiveFirestoreEntity.order,
                logoDeferred = cs.async(coroutineContext.job) {
                    contentRepository.getFile(
                        pathToFolder = competitiveFirestoreEntity.getDocumentName(),
                        fileName = competitiveFirestoreEntity.logo
                    )
                }
            )
        }.toList()
    }
}