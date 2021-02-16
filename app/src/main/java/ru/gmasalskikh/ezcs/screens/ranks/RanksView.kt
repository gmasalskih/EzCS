package ru.gmasalskikh.ezcs.screens.ranks

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.widget.Ranks

class RanksView(
    override val vm: RanksViewModel
) :BaseView<RanksViewModel>(){

    @Composable
    override fun SetContent() {
        Ranks()
    }
}