package ru.gmasalskikh.ezcs.screens.splash_screen

import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class SplashScreenViewEvent : ViewEvent {
    object NavigateNext : SplashScreenViewEvent()
}