package ru.gmasalskikh.ezcs.data.types

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable

sealed class ScreenType {
    object FullScreen : ScreenType()
    data class WithAppBar(
        val appBarTitle: String,
        val additionActionContent: (@Composable BoxScope.() -> Unit)? = null
    ) : ScreenType()
}