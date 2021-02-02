package ru.gmasalskikh.ezcs.screens.splash_screen

import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.screens.ViewState

data class SplashScreenViewState(
    override val viewStateType: ViewStateType = ViewStateType.Data
) : ViewState