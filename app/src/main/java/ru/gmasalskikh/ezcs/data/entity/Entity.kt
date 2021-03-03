package ru.gmasalskikh.ezcs.data.entity

import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.utils.toValidId

interface Entity {
    val name: String
    val entityType: EntityType
    fun getDocumentName() = "${entityType.name}/${name.toValidId()}"
}