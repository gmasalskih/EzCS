package ru.gmasalskikh.ezcs.providers.scope_manager

import kotlinx.coroutines.flow.FlowCollector
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeDefinition
import ru.gmasalskikh.ezcs.di.ScopeName
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewModel
import kotlin.reflect.KClass


interface ScopeManager {
    fun <T : ScopeClosable> getScopedInstance(scopeName: ScopeName, clazz: KClass<T>): T
    fun closeScope(scopeName: ScopeName)
}