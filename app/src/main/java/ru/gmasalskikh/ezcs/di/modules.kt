package ru.gmasalskikh.ezcs.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.gmasalskikh.ezcs.providers.mapper.ResourceMapper
import ru.gmasalskikh.ezcs.providers.mapper.ResourceMapperImpl
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolderImpl
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewModel
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewModel
import ru.gmasalskikh.ezcs.screens.ranks.RanksViewModel
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenViewModel
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenViewState
import ru.gmasalskikh.ezcs.screens.test.TestVM
import ru.gmasalskikh.ezcs.utils.SHARED_PREFERENCES_NAME

val appStateModule = module {
    single<AppStateHolder> { AppStateHolderImpl }
}

val providerModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
    factory<ResourceMapper> { ResourceMapperImpl() }
}

val viewModelModule = module {
    viewModel {
        SplashScreenViewModel(
            sharedPreferences = get()
        )
    }
    viewModel { PreviewViewModel() }
    viewModel { MainMenuViewModel() }
    viewModel { RanksViewModel() }
    viewModel { TestVM() }
}

val viewStateModule = module {
    factory { SplashScreenViewState() }
}