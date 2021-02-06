package ru.gmasalskikh.ezcs.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<VS : ViewState> : ViewModel() {

    private var isViewModelAttach: Boolean = false
    protected abstract val currentTargetNavigation: TargetNavigation
    protected abstract val defaultViewState: VS
    protected val customViewModelScope = object : CustomViewModelCoroutineScope {
        private var job = Job()
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + job

        override fun onStart() {
            job = Job()
        }

        override fun onStop() {
            job.cancel()
        }
    }

    abstract var screenState: ScreenState<VS>
        protected set

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

    open fun onViewCreate(navController: NavController) {
        addOnDestinationChangedListener(navController)
    }

    open fun onViewDestroy() {

    }

    protected open fun onViewModelAttach() {
        isViewModelAttach = true
        customViewModelScope.onStart()
        Log.d("---", "onViewModelAttach ${this::class.java.simpleName}")
    }

    protected open fun onViewModelDetach() {
        isViewModelAttach = false
        customViewModelScope.onStop()
        Log.d("---", "onViewModelDetach ${this::class.java.simpleName}")
    }

    private fun addOnDestinationChangedListener(navController: NavController) {
        if (!isViewModelAttach) {
            onViewModelAttach()
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id != currentTargetNavigation.navId) onViewModelDetach()
            }
        }
    }

    protected interface CustomViewModelCoroutineScope : CoroutineScope {
        fun onStart()
        fun onStop()
    }
}