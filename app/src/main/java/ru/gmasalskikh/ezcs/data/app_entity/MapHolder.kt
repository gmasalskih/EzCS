package ru.gmasalskikh.ezcs.data.app_entity

import android.graphics.Bitmap
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.data.type.EntityType

class MapHolder (
    val isCompetitive: Boolean = true,
    val logoDescription: String,
    val logoDeferred: Deferred<Bitmap>,
    val mapDescription: String,
    val mapDeferred: Deferred<Bitmap>,
    val wallpaperDescription: String,
    val wallpaperDeferred: Deferred<Bitmap>,
): AppEntity