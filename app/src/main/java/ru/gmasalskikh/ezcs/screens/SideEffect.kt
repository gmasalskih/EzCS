package ru.gmasalskikh.ezcs.screens

sealed class SideEffect {
    object Data : SideEffect()
    object Loading : SideEffect()
    data class Error(val msgErr: String? = null, val err: Throwable? = null) : SideEffect()
}