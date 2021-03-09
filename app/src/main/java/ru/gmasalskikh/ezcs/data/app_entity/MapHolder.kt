package ru.gmasalskikh.ezcs.data.app_entity

import android.graphics.Bitmap
import kotlinx.coroutines.Deferred

class MapHolder (
    val name: String,
    val isCompetitive: Boolean = true,
    val logoDescription: String,
    val logoDeferred: Deferred<Bitmap>,
    val mapDescription: String,
    val mapDeferred: Deferred<Bitmap>,
    val wallpaperDescription: String,
    val wallpaperDeferred: Deferred<Bitmap>,
): AppEntity