package ru.gmasalskikh.ezcs.providers.data_repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.suspendCoroutine

class DataRepositoryImpl(
    private val firestore: FirebaseFirestore
) : DataRepository {

    override suspend fun <T> getListDocuments(
        collectionName: String,
        clazz: Class<T>
    ) = suspendCoroutine<List<T>> { continuation ->
        firestore.collection(collectionName).get()
            .addOnSuccessListener { documents ->
                documents.documents
                    .mapNotNull { it.toObject(clazz) }
                    .toList()
                    .let { list -> Result.success(list) }
                    .let { result -> continuation.resumeWith(result) }
            }.addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }

    override suspend fun <T> getDocument(
        collectionName: String,
        documentName: String,
        clazz: Class<T>
    ) = suspendCoroutine<T> { continuation ->
        firestore.collection(collectionName).document(documentName).get()
            .addOnSuccessListener { document ->
                document.toObject(clazz)
                    ?.let { obj -> Result.success(obj) }
                    ?.let { result -> continuation.resumeWith(result) }
                    ?: continuation.resumeWith(
                        Result.failure(
                            IllegalArgumentException(
                                "$collectionName/$document is not contains ${clazz.simpleName}"
                            )
                        )
                    )
            }.addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }
}