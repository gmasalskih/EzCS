package ru.gmasalskikh.ezcs.providers.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.data.types.RanksBottomAppBarItemType
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewState
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewState

interface ResourceMapper {
    @StringRes
    fun getStringRes(mainMenuItemType: MainMenuViewState.MainMenuItemType):Int

    @StringRes
    fun getStringRes(ranksBottomAppBarItemType: RanksBottomAppBarItemType):Int

    @DrawableRes
    fun getDrawableRes(mainMenuItemType: MainMenuViewState.MainMenuItemType):Int

    @DrawableRes
    fun getDrawableRes(itemType: PreviewViewState.PreviewItemType):Int

    @DrawableRes
    fun getDrawableRes(ranksBottomAppBarItemType: RanksBottomAppBarItemType):Int
}