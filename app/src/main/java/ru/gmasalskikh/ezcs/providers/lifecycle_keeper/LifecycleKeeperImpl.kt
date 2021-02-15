package ru.gmasalskikh.ezcs.providers.lifecycle_keeper

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.gmasalskikh.ezcs.providers.lifecycle_keeper.LifecycleKeeper.*

@Suppress("ObjectPropertyName")
object LifecycleKeeperImpl : LifecycleKeeper {
    private val _lifecycleActivity = MutableSharedFlow<LifecycleActivityEvent>()

    override val lifecycleEmitter: FlowCollector<LifecycleActivityEvent> = _lifecycleActivity

    override val lifecycleFlow: SharedFlow<LifecycleActivityEvent> = _lifecycleActivity.asSharedFlow()
}