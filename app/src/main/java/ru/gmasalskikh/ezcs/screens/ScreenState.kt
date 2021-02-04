package ru.gmasalskikh.ezcs.screens

import ru.gmasalskikh.ezcs.data.types.ScreenType
import ru.gmasalskikh.ezcs.data.types.ViewStateType

data class ScreenState<VS : ViewState>(
    val screenType: ScreenType,
    val viewStateType: ViewStateType,
    val viewState: VS
)