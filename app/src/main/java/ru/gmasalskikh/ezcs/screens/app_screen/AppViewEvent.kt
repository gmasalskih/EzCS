package ru.gmasalskikh.ezcs.screens.app_screen

sealed class AppViewEvent {
    object OnBackClick : AppViewEvent()
    object OnMenuClick : AppViewEvent()
    object Nop : AppViewEvent()
}