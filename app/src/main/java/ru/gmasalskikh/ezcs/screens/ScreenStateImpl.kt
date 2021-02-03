package ru.gmasalskikh.ezcs.screens

import ru.gmasalskikh.ezcs.data.types.ScreenType

data class ScreenStateImpl<VS : ViewState>(
    override val screenType: ScreenType,
    override val viewSate: VS
) : ScreenState<VS>