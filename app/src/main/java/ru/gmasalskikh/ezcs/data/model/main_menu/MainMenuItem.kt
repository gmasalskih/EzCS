package ru.gmasalskikh.ezcs.data.model.main_menu

import androidx.annotation.DrawableRes

data class MainMenuItem(
    @DrawableRes
    val drawableRes: Int,
    val type: MainMenuItemType
)