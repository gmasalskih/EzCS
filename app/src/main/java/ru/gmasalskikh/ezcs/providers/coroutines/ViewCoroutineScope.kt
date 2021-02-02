package ru.gmasalskikh.ezcs.providers.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ViewCoroutineScope : CoroutineScope {
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun onStart() {
        job = Job()
    }

    fun onStop() {
        job.cancel()
    }
}