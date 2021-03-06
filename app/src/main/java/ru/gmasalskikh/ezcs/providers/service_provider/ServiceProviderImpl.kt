package ru.gmasalskikh.ezcs.providers.service_provider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import ru.gmasalskikh.ezcs.data.firestore_entities.CompetitiveFirestoreEntity
import ru.gmasalskikh.ezcs.data.pojo.Competitive
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.content_repository.ContentRepository
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.data_repository.DataRepository

class ServiceProviderImpl(
    private val dataRepository: DataRepository,
    private val contentRepository: ContentRepository,
    private val cs: CustomCoroutineScope
) : ServiceProvider {

    override suspend fun getCompetitiveList(): List<Competitive> = withContext(Dispatchers.IO) {
        dataRepository.getListEntities(
            EntityType.COMPETITIVE,
            CompetitiveFirestoreEntity::class.java
        ).asFlow().map { competitiveFirestoreEntity ->
            Competitive(
                name = competitiveFirestoreEntity.name,
                order = competitiveFirestoreEntity.order,
                logoName = competitiveFirestoreEntity.logo,
                logoDeferred = async {
                    contentRepository.getFile(
                        pathToFolder = competitiveFirestoreEntity.getDocumentName(),
                        fileName = competitiveFirestoreEntity.logo
                    )
                },
            )
        }.toList()
    }
}