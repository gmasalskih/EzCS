package ru.gmasalskikh.ezcs.data.types

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable

sealed class ScreenType {
    object FullScreen : ScreenType()
    data class WithAppBar(
        @StringRes
        val appBarTitle: Int,
        val additionActionContent: (@Composable BoxScope.() -> Unit)? = null
    ) : ScreenType()
}