package ru.gmasalskikh.ezcs.screens.ranks

import org.orbitmvi.orbit.Container
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.screens.BaseViewModel

class RanksViewModel : BaseViewModel<RanksViewState, Nothing>(
    defaultViewState = RanksViewState(),
    initViewStateType = ViewStateType.Data
) {
    override val container: Container<RanksViewState, Nothing>
        get() = TODO("Not yet implemented")
}