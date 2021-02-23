package ru.gmasalskikh.ezcs.providers.lifecycle_holder

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow

interface LifecycleHolder {
    val lifecycleEmitter: FlowCollector<LifecycleActivityEvent>
    val lifecycleCollector: SharedFlow<LifecycleActivityEvent>

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