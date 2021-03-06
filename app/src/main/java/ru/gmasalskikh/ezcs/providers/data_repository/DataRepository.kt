package ru.gmasalskikh.ezcs.providers.data_repository

interface DataRepository {
    suspend fun <T> getListDocuments(collectionName: String, clazz: Class<T>): List<T>
    suspend fun <T> getDocument(collectionName: String, documentName: String, clazz: Class<T>): T
}