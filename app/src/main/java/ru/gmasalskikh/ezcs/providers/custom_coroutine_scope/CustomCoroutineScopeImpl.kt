package ru.gmasalskikh.ezcs.providers.custom_coroutine_scope

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class CustomCoroutineScopeImpl(
    private val dispatcher: CoroutineDispatcher
) : CustomCoroutineScope {
    var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

    override fun onStart() {
        job = Job()
    }

    override fun onStop() {
        job.cancel()
    }
}