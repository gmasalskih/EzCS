package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

data class AppState(
    val drawerGesturesEnabled: Boolean = false,
    val isAppBackgroundBlur: Boolean = false,
    val appBarState: AppBarState = AppBarState.AppBarGone,
    val bottomBarState: BottomBarState = BottomBarState.BottomBarGone
) {
    sealed class AppBarState {
        object AppBarGone : AppBarState()
        data class AppBar(
            @StringRes
            val titleRes: Int,
            val navContentType: AppBarNavContentType
        ) : AppBarState() {
            enum class AppBarNavContentType(val image: ImageVector) {
                MENU(image = Icons.Filled.Menu),
                ARROW_BACK(image = Icons.Filled.KeyboardArrowLeft)
            }
        }
    }

    sealed class BottomBarState {
        object BottomBarGone : BottomBarState()
        data class BottomBar(val listBottomItem: List<BottomItem> = listOf()) : BottomBarState()
    }

    data class BottomItem(
        @StringRes
        val itemName: Int,

        @DrawableRes
        val itemIcon: Int
    )
}