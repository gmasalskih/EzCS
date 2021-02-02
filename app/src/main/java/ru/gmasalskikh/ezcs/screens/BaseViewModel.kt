package ru.gmasalskikh.ezcs.screens

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VS : ViewState> : ViewModel() {

    protected abstract val defaultViewState: VS

    abstract var viewState: VS
        protected set

    open fun onViewCreate() {
        Log.d("---", "onViewCreate ${this::class.java.simpleName}")
    }

    open fun onViewDestroy(){
        Log.d("---", "onViewDestroy ${this::class.java.simpleName}")
    }

}