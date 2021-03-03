package ru.gmasalskikh.ezcs.providers.service_provider

import ru.gmasalskikh.ezcs.providers.content_repository.ContentRepository
import ru.gmasalskikh.ezcs.providers.data_repository.DataRepository

class ServiceProviderImpl(
    private val dataRepository: DataRepository,
    private val contentRepository: ContentRepository
) : ServiceProvider {

}