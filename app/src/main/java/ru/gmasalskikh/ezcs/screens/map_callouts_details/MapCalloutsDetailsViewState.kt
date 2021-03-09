package ru.gmasalskikh.ezcs.screens.map_callouts_details

import android.graphics.Bitmap
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class MapCalloutsDetailsViewState(
    override var currentSideEffect: SideEffect = SideEffect.Data,
    val mapCalloutsBitmap: Bitmap? = null
) : ViewState