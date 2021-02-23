package ru.gmasalskikh.ezcs.di

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
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
import ru.gmasalskikh.ezcs.screens.grenades_practice.GrenadesPracticeViewModel
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsViewModel
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveViewModel
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneViewModel
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankViewModel
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanViewModel
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewModel

enum class NamesOfDependencies {
    LIFECYCLE_EMITTER,
    LIFECYCLE_COLLECTOR,
    NAV_EVENT_EMITTER,
    NAV_EVENT_COLLECTOR,
    APP_VIEW_EVENT_COLLECTOR,
    APP_VIEW_EVENT_EMITTER,
}

val emittersModule = module {
    factory(named(LIFECYCLE_EMITTER)) { get<LifecycleHolder>().lifecycleEmitter }
    factory(named(NAV_EVENT_EMITTER)) { get<Navigator>().navEventEmitter }
    factory(named(APP_VIEW_EVENT_EMITTER)) { get<AppStateHolder>().appViewEventEmitter }
}

val collectorsModule = module {
    factory(named(LIFECYCLE_COLLECTOR)) { get<LifecycleHolder>().lifecycleCollector }
    factory(named(NAV_EVENT_COLLECTOR)) { get<Navigator>().navEventCollector }
    factory(named(APP_VIEW_EVENT_COLLECTOR)) { get<AppStateHolder>().appViewEventCollector }
}

val appStateModule = module {
    single<AppStateHolder> {
        AppStateHolderImpl(
            cs = get { parametersOf(Dispatchers.Main) },
            navEventEmitter = get(named(NAV_EVENT_EMITTER)),
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
    single<Navigator> { NavigatorImpl(get { parametersOf(Dispatchers.Main) }) }
}

val viewModelModule = module {
    viewModel {
        SplashScreenViewModel(
            sharedPreferences = get(),
            navEventEmitter = get(named(NAV_EVENT_EMITTER)),
        )
    }
    viewModel {
        PreviewViewModel(navEventEmitter = get(named(NAV_EVENT_EMITTER)))
    }
    viewModel {
        MainMenuViewModel(navEventEmitter = get(named(NAV_EVENT_EMITTER)))
    }
    viewModel { MapCalloutsViewModel() }
    viewModel { WeaponCharacteristicsViewModel() }
    viewModel { GrenadesPracticeViewModel() }
    viewModel { CompetitiveViewModel() }
    viewModel { DangerZoneViewModel() }
    viewModel { ProfileRankViewModel() }
    viewModel { WingmanViewModel() }
}