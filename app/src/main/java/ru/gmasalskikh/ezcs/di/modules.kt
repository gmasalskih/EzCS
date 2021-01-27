package ru.gmasalskikh.ezcs.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewModel
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewModel
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenViewModel
import ru.gmasalskikh.ezcs.utils.SHARED_PREFERENCES_NAME


val providerModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}

val viewModelModule = module {
    viewModel { (sp: SharedPreferences) -> SplashScreenViewModel(sp) }
    viewModel { PreviewViewModel() }
    viewModel { MainMenuViewModel() }
}