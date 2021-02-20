package ru.gmasalskikh.ezcs.screens

import android.os.Parcelable
import ru.gmasalskikh.ezcs.data.types.ViewStateType

interface ViewState : Parcelable {
    data class ScreenState<VS : ViewState>(
        val viewStateType: ViewStateType,
        val viewState: VS
    )
}