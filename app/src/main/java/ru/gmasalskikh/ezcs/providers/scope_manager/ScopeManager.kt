package ru.gmasalskikh.ezcs.providers.scope_manager

import ru.gmasalskikh.ezcs.di.ScopeName
import kotlin.reflect.KClass


interface ScopeManager {
    fun <T : ScopeClosable> getScopedInstance(scopeName: ScopeName, clazz: KClass<T>): T
    fun closeScope(scopeName: ScopeName)
}