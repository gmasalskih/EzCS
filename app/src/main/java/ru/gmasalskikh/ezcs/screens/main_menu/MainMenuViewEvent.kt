package ru.gmasalskikh.ezcs.screens.main_menu

import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class MainMenuViewEvent : ViewEvent {
    data class NavigateTo(
        val mainMenuItemType: MainMenuViewState.MainMenuItemType
    ) : MainMenuViewEvent()
}