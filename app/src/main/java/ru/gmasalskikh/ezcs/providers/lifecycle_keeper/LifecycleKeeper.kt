package ru.gmasalskikh.ezcs.providers.lifecycle_keeper

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow

interface LifecycleKeeper {
    val lifecycleEmitter: FlowCollector<LifecycleActivityEvent>
    val lifecycleFlow: SharedFlow<LifecycleActivityEvent>

    enum class LifecycleActivityEvent {
        ON_CREATE,
        ON_START,
        ON_RESTART,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
    }
}