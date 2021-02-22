package ru.gmasalskikh.ezcs.screens.ranks.wingman

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.wingman.widgets.WingmanContent

class WingmanView(
    vm: WingmanViewModel
) : BaseView<WingmanViewState, Nothing, WingmanViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: WingmanViewState) {
        WingmanContent(text = viewState.name)
    }
}