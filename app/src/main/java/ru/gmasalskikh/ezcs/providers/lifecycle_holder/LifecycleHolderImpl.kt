package ru.gmasalskikh.ezcs.providers.lifecycle_holder

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.gmasalskikh.ezcs.providers.lifecycle_holder.LifecycleHolder.*

@Suppress("ObjectPropertyName")
class LifecycleHolderImpl : LifecycleHolder {
    private val _lifecycleActivity = MutableSharedFlow<LifecycleActivityEvent>()
    override val lifecycleEmitter: FlowCollector<LifecycleActivityEvent> = _lifecycleActivity
    override val lifecycleCollector: SharedFlow<LifecycleActivityEvent> = _lifecycleActivity.asSharedFlow()
}