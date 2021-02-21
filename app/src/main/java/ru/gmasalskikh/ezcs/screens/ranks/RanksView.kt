package ru.gmasalskikh.ezcs.screens.ranks

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView

class RanksView(vm: RanksViewModel) :
    BaseView<RanksViewState, RanksViewEvent, RanksViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: RanksViewState) {
        Text(
            text = viewState.name
        )
    }
}