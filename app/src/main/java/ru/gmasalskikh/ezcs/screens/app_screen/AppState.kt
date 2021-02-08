package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AppState(
    val isDrawerEnable: Boolean = true,
    val appBarState: AppBarState = AppBarState.AppBarGone,
    val bottomBarState: BottomBarState = BottomBarState.BottomBarGone
) {
    sealed class AppBarState {
        object AppBarGone : AppBarState()
        data class AppBar(val title: String) : AppBarState()
    }

    sealed class BottomBarState {
        object BottomBarGone : BottomBarState()
        data class BottomBar(val listBottomItem: List<BottomItem> = listOf()): BottomBarState()
    }
    data class BottomItem(
        @StringRes
        val itemName: Int,

        @DrawableRes
        val itemIcon: Int
    )
}