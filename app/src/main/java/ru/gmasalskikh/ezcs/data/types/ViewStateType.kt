package ru.gmasalskikh.ezcs.data.types

sealed class ViewStateType {
    object Data : ViewStateType()
    object Loading : ViewStateType()
    data class Error(val msgErr: String? = null, val err: Throwable? = null)
}