package ru.gmasalskikh.ezcs.screens

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<SS : ScreenState<*>> : ViewModel() {

    protected abstract val defaultViewState: SS

    abstract var screenState: SS
        protected set

    open fun showDate() {

    }

    open fun showLoading() {

    }

    open fun showErr(err: Throwable) {

    }

    open fun onViewCreate() {
        Log.d("---", "onViewCreate ${this::class.java.simpleName}")
    }

    open fun onViewDestroy() {
        Log.d("---", "onViewDestroy ${this::class.java.simpleName}")
    }

}