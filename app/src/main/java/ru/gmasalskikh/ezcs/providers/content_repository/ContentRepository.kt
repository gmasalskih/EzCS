package ru.gmasalskikh.ezcs.providers.content_repository

import android.graphics.Bitmap

interface ContentRepository {
    suspend fun getFileThumbnail(pathToFolder: String, fileName: String): Bitmap
    suspend fun getFile(pathToFolder: String, fileName: String): Bitmap
    suspend fun getFileLink(pathToFolder: String, fileName: String): String
}