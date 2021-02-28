package ru.gmasalskikh.ezcs.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.ViewModelStore
import androidx.navigation.compose.*
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.scope.getViewModel
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import ru.gmasalskikh.ezcs.di.NamesOfScopes
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.screens.grenades_practice.GrenadesPracticeView
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsView
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveView
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneView
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankView
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanView
import ru.gmasalskikh.ezcs.navigation.TargetNavigation.*
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewModel

@Composable
fun ComposeNavigation(navigator: Navigator = get()) {
    val navController = AmbientNavController.current
    DisposableEffect(key1 = null) {
        navigator.onAttach(navController)
        onDispose {
            navigator.onDetach()
        }
    }
    NavHost(
        navController = navController,
        startDestination = SplashScreen.path
    ) {
        composable(SplashScreen.path) {
            SplashScreenView(getViewModel()).Screen()
        }
        composable(Preview().path) {
            PreviewView(getViewModel()).Screen()
        }
        composable(MainMenu().path) {
            MainMenuView(getViewModel()).Screen()
            get<Scope> { parametersOf(NamesOfScopes.WEAPON_CHARACTERISTICS_SCOPE) }.run {
                Log.d("---", "MainMenuView $closed")
                if (!closed) get<WeaponCharacteristicsViewModel>().destroy()
                close()
            }
        }
        composable(MapCallouts.path) {
            MapCalloutsView(getViewModel()).Screen()
        }
        navigation(
            startDestination = WeaponCharacteristicsPistol().path,
            route = WeaponCharacteristics.path,
        ) {
            composable(WeaponCharacteristicsPistol().path) {
                get<Scope> { parametersOf(NamesOfScopes.WEAPON_CHARACTERISTICS_SCOPE) }.let {
                    WeaponCharacteristicsView(it.get()).Screen()
                }
            }
            composable(WeaponCharacteristicsHeavy().path) {
                get<Scope> { parametersOf(NamesOfScopes.WEAPON_CHARACTERISTICS_SCOPE) }.let {
                    WeaponCharacteristicsView(it.get()).Screen()
                }
            }
            composable(WeaponCharacteristicsSMG().path) {
                get<Scope> { parametersOf(NamesOfScopes.WEAPON_CHARACTERISTICS_SCOPE) }.let {
                    WeaponCharacteristicsView(it.get()).Screen()
                }
            }
            composable(WeaponCharacteristicsRifle().path) {
                get<Scope> { parametersOf(NamesOfScopes.WEAPON_CHARACTERISTICS_SCOPE) }.let {
                    WeaponCharacteristicsView(it.get()).Screen()
                }
            }
        }
        composable(GrenadesPractice.path) {
            GrenadesPracticeView(getViewModel()).Screen()
        }
        navigation(
            startDestination = RanksCompetitive().path,
            route = Ranks.path
        ) {
            composable(RanksCompetitive().path) {
                CompetitiveView(getViewModel()).Screen()
            }
            composable(RanksWingman().path) {
                WingmanView(getViewModel()).Screen()
            }
            composable(RanksDangerZone().path) {
                DangerZoneView(getViewModel()).Screen()
            }
            composable(RanksProfileRank().path) {
                ProfileRankView(getViewModel()).Screen()
            }
        }
    }
}