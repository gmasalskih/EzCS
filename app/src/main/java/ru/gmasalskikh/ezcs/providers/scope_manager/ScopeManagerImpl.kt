package ru.gmasalskikh.ezcs.providers.scope_manager

import org.koin.core.Koin
import org.koin.core.qualifier.named
import ru.gmasalskikh.ezcs.di.ScopeName
import kotlin.reflect.KClass

class ScopeManagerImpl(
    private val koin: Koin
) : ScopeManager {

    private val storageInstance = mutableMapOf<String, MutableSet<ScopeClosable>>()

    override fun <T : ScopeClosable> getScopedInstance(scopeName: ScopeName, clazz: KClass<T>): T {
        return koin.getOrCreateScope(scopeName.id, named(scopeName))
            .apply { if (closed) scopeName.setNewId() }
            .get(clazz = clazz)
            .also {
                if (storageInstance.containsKey(scopeName.id)) storageInstance[scopeName.id]?.add(it)
                else storageInstance[scopeName.id] = mutableSetOf(it)
            }
    }

    override fun closeScope(scopeName: ScopeName) {
        koin.getScopeOrNull(scopeName.id)?.let { scope ->
            storageInstance.remove(scopeName.id)?.forEach { it.close() }
            scope.close()
        }
    }
}