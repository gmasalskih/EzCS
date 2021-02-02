package ru.gmasalskikh.ezcs.providers.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.data.types.MainMenuItemType
import ru.gmasalskikh.ezcs.data.types.PreviewItemType
import ru.gmasalskikh.ezcs.data.types.RanksBottomAppBarItemType

interface ResourceMapper {
    @StringRes
    fun getStringRes(mainMenuItemType: MainMenuItemType):Int

    @StringRes
    fun getStringRes(ranksBottomAppBarItemType: RanksBottomAppBarItemType):Int

    @DrawableRes
    fun getDrawableRes(mainMenuItemType: MainMenuItemType):Int

    @DrawableRes
    fun getDrawableRes(itemType: PreviewItemType):Int

    @DrawableRes
    fun getDrawableRes(ranksBottomAppBarItemType: RanksBottomAppBarItemType):Int
}