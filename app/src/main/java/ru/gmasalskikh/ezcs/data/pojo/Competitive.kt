package ru.gmasalskikh.ezcs.data.pojo

import android.graphics.Bitmap
import kotlinx.coroutines.Deferred

data class Competitive(
    val name: String = "",
    val logoName: String = "",
    val logoDeferred: Deferred<Bitmap>? = null,
    val order: Int = 0
)