package ru.gmasalskikh.ezcs.screens

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import ru.gmasalskikh.ezcs.data.types.ScreenType
import ru.gmasalskikh.ezcs.data.types.ViewStateType

abstract class BaseViewModel<VS : ViewState>(
    private val defaultViewState: VS,
    private val navId: Int
) : ViewModel() {

    private var destinationChangedListener: NavController.OnDestinationChangedListener? = null

    var screenState: ScreenState<VS> by mutableStateOf(
        ScreenState(
            screenType = ScreenType.FullScreen,
            viewStateType = ViewStateType.Loading,
            viewState = defaultViewState
        )
    )
        protected set

    fun addOnDestinationChangedListener(navController: NavController) {
        if (destinationChangedListener == null){
            val dcl = object : NavController.OnDestinationChangedListener{
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    if (destination.id != navId) {
                        onDestinationChanged()
                        controller.removeOnDestinationChangedListener(this)
                    }
                }
            }
            destinationChangedListener = dcl
            navController.addOnDestinationChangedListener(dcl)
        }
    }

    open fun setDefaultState() {
        screenState = screenState.copy(viewState = defaultViewState)
    }

    open fun showDate() {
        screenState = screenState.copy(viewStateType = ViewStateType.Data)
    }

    open fun showLoading() {
        screenState = screenState.copy(viewStateType = ViewStateType.Loading)
    }

    open fun showErr(err: Throwable) {
        screenState = screenState.copy(
            viewStateType = ViewStateType.Error(msgErr = err.message, err = err)
        )
    }

    open fun onViewCreate() {
        Log.d("---", "onViewCreate ${this::class.java.simpleName}")
    }

    open fun onViewDestroy() {
        Log.d("---", "onViewDestroy ${this::class.java.simpleName}")
    }

    protected open fun onDestinationChanged() {
        Log.d("---", "onDestinationChanged ${this::class.java.simpleName}")
    }
}