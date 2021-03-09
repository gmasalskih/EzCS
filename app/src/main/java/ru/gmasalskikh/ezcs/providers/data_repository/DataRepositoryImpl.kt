package ru.gmasalskikh.ezcs.providers.data_repository

import com.google.firebase.firestore.FirebaseFirestore
import ru.gmasalskikh.ezcs.data.type.EntityType
import kotlin.coroutines.suspendCoroutine

class DataRepositoryImpl(
    private val firestore: FirebaseFirestore
) : DataRepository {

    override suspend fun <T> getListEntities(
        entityType: EntityType,
        clazz: Class<T>
    ) = suspendCoroutine<List<T>> { continuation ->
        firestore.collection(entityType.name).get()
            .addOnSuccessListener { documents ->
                val list = documents.documents
                    .mapNotNull { it.toObject(clazz) }
                    .toList()
                continuation.resumeWith(Result.success(list))
            }.addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }


    override suspend fun <T> getEntity(
        entityType: EntityType,
        entityName: String,
        clazz: Class<T>
    ) = suspendCoroutine<T> { continuation ->
        firestore.collection(entityType.name).document(entityName).get()
            .addOnSuccessListener { document ->
                document.toObject(clazz)
                    ?.let { result -> continuation.resumeWith(Result.success(result)) }
                    ?: continuation.resumeWith(
                        Result.failure(
                            IllegalArgumentException(
                                "${entityType.name}/$document is not contains ${clazz.simpleName}"
                            )
                        )
                    )
            }.addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }

}