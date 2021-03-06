package ru.gmasalskikh.ezcs.providers.content_repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.dropbox.core.v2.files.DbxUserFilesRequests
import com.dropbox.core.v2.files.PathOrLink
import com.dropbox.core.v2.files.ThumbnailFormat
import com.dropbox.core.v2.files.ThumbnailSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

class ContentRepositoryImpl(
    private val dropbox: DbxUserFilesRequests
) : ContentRepository {

    override suspend fun getFile(
        pathToFolder: String,
        fileName: String
    ) = withContext(Dispatchers.IO) {
        suspendCoroutine<Bitmap> { continuation ->
            try {
                dropbox.download("${pathToFolder.toValidPath()}/$fileName")
                    .inputStream
                    .let { inputStream -> BitmapFactory.decodeStream(inputStream) }
                    .let { bitmap -> Result.success(bitmap) }
                    .let { result -> continuation.resumeWith(result) }
            } catch (e: Throwable) {
                continuation.resumeWith(Result.failure(e))
            }
        }
    }

    override suspend fun getFileThumbnail(
        pathToFolder: String,
        fileName: String
    ) = suspendCoroutine<Bitmap> { continuation ->
        try {
            dropbox
                .getThumbnailV2Builder(PathOrLink.path("${pathToFolder.toValidPath()}/$fileName"))
                .withFormat(ThumbnailFormat.PNG)
                .withSize(ThumbnailSize.W128H128)
                .start()
                .inputStream
                .let { inputStream -> BitmapFactory.decodeStream(inputStream) }
                .let { bitmap -> Result.success(bitmap) }
                .let { result -> continuation.resumeWith(result) }
        } catch (e: Throwable) {
            continuation.resumeWith(Result.failure(e))
        }
    }


    override suspend fun getFileLink(
        pathToFolder: String,
        fileName: String
    ) = suspendCoroutine<String> { continuation ->
        try {
            dropbox.getTemporaryLink("${pathToFolder.toValidPath()}/$fileName")
                .let { response -> Result.success(response.link) }
                .let { result -> continuation.resumeWith(result) }
        } catch (e: Throwable) {
            continuation.resumeWith(Result.failure(e))
        }
    }


    private fun String.toValidPath() = if (this.contains("^[/]".toRegex())) this else "/$this"
}