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
import ru.gmasalskikh.ezcs.providers.lifecycle_keeper.LifecycleKeeper
import ru.gmasalskikh.ezcs.providers.lifecycle_keeper.LifecycleKeeperImpl
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolderImpl
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewModel
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewModel
import ru.gmasalskikh.ezcs.screens.ranks.RanksViewModel
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenViewModel
import ru.gmasalskikh.ezcs.utils.SHARED_PREFERENCES_NAME
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.NavigatorImpl
import ru.gmasalskikh.ezcs.di.NamesOfDependencies.*

enum class NamesOfDependencies {
    LIFECYCLE_EMITTER,
    NAV_EVENT_EMITTER
}

val emittersModule = module {
    factory(named(LIFECYCLE_EMITTER)) { get<LifecycleKeeper>().lifecycleEmitter }
    factory(named(NAV_EVENT_EMITTER)) { get<Navigator>().navEventEmitter }
}

val appStateModule = module {
    single<AppStateHolder> { AppStateHolderImpl(get { parametersOf(Dispatchers.Main) }) }
}

val providerModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
    factory<CustomCoroutineScope> { (dispatcher: CoroutineDispatcher) ->
        CustomCoroutineScopeImpl(dispatcher)
    }
    single<LifecycleKeeper> { LifecycleKeeperImpl() }
    single<Navigator> { NavigatorImpl(get { parametersOf(Dispatchers.Main) }) }
}

val viewModelModule = module {
    viewModel {
        SplashScreenViewModel(
            sharedPreferences = get(),
            navEventEmitter = get(named(NAV_EVENT_EMITTER))
        )
    }
    viewModel {
        PreviewViewModel(navEventEmitter = get(named(NAV_EVENT_EMITTER)))
    }
    viewModel {
        MainMenuViewModel(navEventEmitter = get(named(NAV_EVENT_EMITTER)))
    }
    viewModel { RanksViewModel() }
}