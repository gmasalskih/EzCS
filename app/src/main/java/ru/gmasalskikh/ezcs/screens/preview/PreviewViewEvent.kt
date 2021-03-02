package ru.gmasalskikh.ezcs.screens.preview

import ru.gmasalskikh.ezcs.screens.ViewEvent
//import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

sealed class PreviewViewEvent : ViewEvent {
    object NavigateNext : PreviewViewEvent()
//    data class SetPagerState(val pagerState: PagerState) : PreviewViewEvent()
}