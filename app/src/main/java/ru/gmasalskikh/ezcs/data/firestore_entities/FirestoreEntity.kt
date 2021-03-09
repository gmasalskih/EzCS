package ru.gmasalskikh.ezcs.data.firestore_entities

import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.utils.toValidId

interface FirestoreEntity {
    val name: String
    val entityType: EntityType
    fun getDocumentName() = "${entityType.name}/${name.toValidId()}"
}