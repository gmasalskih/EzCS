package ru.gmasalskikh.ezcs.screens

import ru.gmasalskikh.ezcs.data.types.ViewStateType

interface ViewState {
    data class ScreenState<VS : ViewState>(
        val viewStateType: ViewStateType,
        val viewState: VS
    )
}