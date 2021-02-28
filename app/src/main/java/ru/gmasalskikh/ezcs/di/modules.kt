package ru.gmasalskikh.ezcs.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScopeImpl
import ru.gmasalskikh.ezcs.providers.lifecycle_holder.LifecycleHolder
import ru.gmasalskikh.ezcs.providers.lifecycle_holder.LifecycleHolderImpl
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolderImpl
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewModel
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewModel
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenViewModel
import ru.gmasalskikh.ezcs.utils.SHARED_PREFERENCES_NAME
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.NavigatorImpl
import ru.gmasalskikh.ezcs.di.NamesOfDependencies.*
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.app_controller.AppControllerImpl
import ru.gmasalskikh.ezcs.screens.grenades_practice.GrenadesPracticeViewModel
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsViewModel
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveViewModel
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneViewModel
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankViewModel
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanViewModel
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewModel
import java.util.*

enum class NamesOfScopes(private var id: UUID = UUID.randomUUID()) {
    WEAPON_CHARACTERISTICS_SCOPE;

    fun getId() = id.toString()

    fun setNewId() {
        id = UUID.randomUUID()
    }
}

enum class NamesOfDependencies {
    LIFECYCLE_EMITTER,
    LIFECYCLE_COLLECTOR,
    NAV_EVENT_EMITTER,
    NAV_EVENT_COLLECTOR,
    APP_VIEW_EVENT_COLLECTOR,
    APP_VIEW_EVENT_EMITTER,
    APP_EVENT_EMITTER
}

val scopeModule = module {
    factory<Scope> { (scopeName: NamesOfScopes) ->
        getKoin().getOrCreateScope(scopeName.getId(), named(scopeName))
            .apply { if (closed) scopeName.setNewId() }
    }
}

val emittersModule = module {
    factory(named(LIFECYCLE_EMITTER)) { get<LifecycleHolder>().lifecycleEmitter }
    factory(named(NAV_EVENT_EMITTER)) { get<Navigator>().targetNavigationEmitter }
    factory(named(APP_EVENT_EMITTER)) { get<AppController>().appEventEmitter }
}

val collectorsModule = module {
    factory(named(NAV_EVENT_COLLECTOR)) { get<Navigator>().navEventCollector }
}

val appStateModule = module {
    single<AppStateHolder> {
        AppStateHolderImpl(
            cs = get { parametersOf(Dispatchers.Main) },
            appEventEmitter = get(named(APP_EVENT_EMITTER)),
            navEventCollector = get(named(NAV_EVENT_COLLECTOR))
        )
    }
}

val providerModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
    factory<CustomCoroutineScope> { (dispatcher: CoroutineDispatcher) ->
        CustomCoroutineScopeImpl(dispatcher)
    }
    single<LifecycleHolder> { LifecycleHolderImpl() }
    single<Navigator> {
        NavigatorImpl(
            cs = get { parametersOf(Dispatchers.Main) }
        )
    }
    single<AppController> {
        AppControllerImpl(
            cs = get { parametersOf(Dispatchers.Main) },
            navEventEmitter = get(named(NAV_EVENT_EMITTER))
        )
    }
}

val viewModelModule = module {
    viewModel {
        SplashScreenViewModel(
            sharedPreferences = get(),
            appEventEmitter = get(named(APP_EVENT_EMITTER)),
        )
    }
    viewModel {
        PreviewViewModel(appEventEmitter = get(named(APP_EVENT_EMITTER)))
    }
    viewModel {
        MainMenuViewModel(appEventEmitter = get(named(APP_EVENT_EMITTER)))
    }
    viewModel { MapCalloutsViewModel() }

    scope(named(NamesOfScopes.WEAPON_CHARACTERISTICS_SCOPE)) {
        scoped { WeaponCharacteristicsViewModel() }
    }

    viewModel { GrenadesPracticeViewModel() }
    viewModel { CompetitiveViewModel() }
    viewModel { DangerZoneViewModel() }
    viewModel { ProfileRankViewModel() }
    viewModel { WingmanViewModel() }
}