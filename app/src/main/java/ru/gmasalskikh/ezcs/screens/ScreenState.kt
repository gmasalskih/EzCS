package ru.gmasalskikh.ezcs.screens

import ru.gmasalskikh.ezcs.data.types.ScreenType

interface ScreenState<VS : ViewState> {
    val screenType: ScreenType
    val viewSate: VS
}