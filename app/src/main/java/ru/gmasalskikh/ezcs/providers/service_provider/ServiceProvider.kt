package ru.gmasalskikh.ezcs.providers.service_provider

import ru.gmasalskikh.ezcs.data.pojo.Competitive

interface ServiceProvider {
    suspend fun getCompetitiveList(): List<Competitive>
}