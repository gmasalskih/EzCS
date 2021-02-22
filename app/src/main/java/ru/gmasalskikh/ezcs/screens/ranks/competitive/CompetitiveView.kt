package ru.gmasalskikh.ezcs.screens.ranks.competitive

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.competitive.widgets.CompetitiveContent

class CompetitiveView(
    vm: CompetitiveViewModel
) : BaseView<CompetitiveViewState, Nothing, CompetitiveViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: CompetitiveViewState) {
        CompetitiveContent(text = viewState.name)
    }
}