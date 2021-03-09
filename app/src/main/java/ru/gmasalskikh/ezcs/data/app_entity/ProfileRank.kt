package ru.gmasalskikh.ezcs.data.app_entity

import android.graphics.Bitmap
import kotlinx.coroutines.Deferred

class ProfileRank(
    val name: String,
    val logoDescription: String,
    val logoDeferred: Deferred<Bitmap>,
    val order: Int,
    val xp: Int,
): AppEntity