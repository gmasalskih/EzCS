package ru.gmasalskikh.ezcs.data.app_entity

import android.graphics.Bitmap
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.data.type.GrenadeType
import ru.gmasalskikh.ezcs.data.type.TickrateType

class MapPoint(
    val name: String,
    val mapDocumentName: String,
    val grenadeType: GrenadeType,
    val tickrateTypes: List<TickrateType>,
    val previewStartDescription: String,
    val previewStartDeferred: Deferred<Bitmap>,
    val previewEndDescription: String,
    val previewEndDeferred: Deferred<Bitmap>,
    val contentImages: List<Deferred<Bitmap>>,
    val contentVideos: List<Deferred<String>>,
): AppEntity