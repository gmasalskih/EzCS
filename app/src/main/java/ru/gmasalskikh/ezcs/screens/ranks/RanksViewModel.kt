package ru.gmasalskikh.ezcs.screens.ranks

import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.screens.BaseViewModel

class RanksViewModel : BaseViewModel<RanksViewState>(
    defaultViewState = RanksViewState(),
    initViewStateType = ViewStateType.Data
) {

}