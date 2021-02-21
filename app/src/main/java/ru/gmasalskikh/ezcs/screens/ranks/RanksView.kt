package ru.gmasalskikh.ezcs.screens.ranks

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.widget.Ranks

class RanksView(vm: RanksViewModel) :
    BaseView<RanksViewEvent, RanksViewState, RanksViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: RanksViewState) {
        Ranks()
    }
}