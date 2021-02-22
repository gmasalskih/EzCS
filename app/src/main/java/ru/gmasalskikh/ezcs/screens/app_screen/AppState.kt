package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath

data class AppState(
    val drawerGesturesEnabled: Boolean = false,
    val isAppBackgroundBlur: Boolean = false,
    val appTopBarState: AppTopBarState = AppTopBarState.NoAppTopBar,
    val appBottomBarState: AppBottomBarState = AppBottomBarState.NoAppBottomBar
) {
    sealed class AppTopBarState {
        object NoAppTopBar : AppTopBarState()
        data class AppTopBar(
            @StringRes
            val titleRes: Int,
            val navContentType: AppTopBarNavContentType
        ) : AppTopBarState() {
            enum class AppTopBarNavContentType(val image: ImageVector) {
                MENU(image = Icons.Filled.Menu),
                ARROW_BACK(image = Icons.Filled.KeyboardArrowLeft)
            }
        }
    }

    sealed class AppBottomBarState {
        object NoAppBottomBar : AppBottomBarState()
        data class AppBottomBar(val listAppBottomBarItem: List<AppBottomBarItem>) :
            AppBottomBarState()
    }

    data class AppBottomBarItem(
        @StringRes
        val itemName: Int,
        @DrawableRes
        val itemIcon: Int? = null,
        val route: TargetNavigationPath,
        val onClick: () -> Unit
    )
}