package ru.gmasalskikh.ezcs.providers.custom_coroutine_scope

import kotlinx.coroutines.CoroutineScope

interface CustomCoroutineScope : CoroutineScope {
    fun onStart()
    fun onStop()
}