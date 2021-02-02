package ru.gmasalskikh.ezcs.screens.ranks

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import ru.gmasalskikh.ezcs.data.types.RanksBottomAppBarItemType

class RanksViewModel : ViewModel() {
    var viewState: RanksViewState by mutableStateOf(RanksViewState())
        private set

    fun setSelectedRank(ranksBottomAppBarItemType: RanksBottomAppBarItemType) {
        viewState = viewState.copy(
            selectedRank = ranksBottomAppBarItemType
        )
    }
}