package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class AppViewState(
    val drawerGesturesEnabled: Boolean = false,
    val isAppBackgroundBlur: Boolean = false,
    val appTopBarState: AppTopBarState = AppTopBarState.NoAppTopBar,
    val appBottomBarState: AppBottomBarState = AppBottomBarState.NoAppBottomBar
) {
    sealed class AppTopBarState {
        object NoAppTopBar : AppTopBarState()
        data class AppTopBar(
            val titleRes: StringResourceType,
            val appTopBarNavItem: AppTopBarNavItem,
            val appTopBarExtraItem: AppTopBarExtraItem? = null
        ) : AppTopBarState()
    }

    sealed class StringResourceType {
        data class StringNative(
            val res: String
        ) : StringResourceType()

        data class StringIdRes(
            @StringRes
            val res: Int
        ) : StringResourceType()
    }

    sealed class AppBottomBarState {
        object NoAppBottomBar : AppBottomBarState()
        data class AppBottomBar(val listAppBottomBarItem: List<AppBottomBarItem>) :
            AppBottomBarState()
    }

    data class AppTopBarNavItem(
        val icon: ImageVector,
        val onClick: suspend () -> Unit
    )

    data class AppTopBarExtraItem(
        val icon: ImageVector,
        val isEnable: Boolean,
        val onClick: suspend () -> Unit
    )

    data class AppBottomBarItem(
        @StringRes
        val label: Int,
        @DrawableRes
        val icon: Int? = null,
        val route: String,
        val onClick: suspend () -> Unit
    )
}