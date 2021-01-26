package ru.gmasalskikh.ezcs.di

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewModel
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewModel


val appModule = module {
    viewModel { (navController: NavController) -> PreviewViewModel(navController) }
    viewModel { (navController: NavController) -> MainMenuViewModel(navController) }

}