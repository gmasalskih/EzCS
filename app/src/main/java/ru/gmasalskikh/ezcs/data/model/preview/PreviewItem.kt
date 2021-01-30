package ru.gmasalskikh.ezcs.data.model.preview

import androidx.annotation.DrawableRes

data class PreviewItem(
    @DrawableRes val drawableRes: Int,
    val type: PreviewItemType
)