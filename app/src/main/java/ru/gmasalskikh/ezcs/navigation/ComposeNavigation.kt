package ru.gmasalskikh.ezcs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.*
import ru.gmasalskikh.ezcs.di.ScopeName
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.utils.LocalNavController
import ru.gmasalskikh.ezcs.screens.grenades_practice.GrenadesPracticeView
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsView
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveView
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneView
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankView
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanView
import ru.gmasalskikh.ezcs.navigation.TargetNavigation.*
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeManager
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun ComposeNavigation(
    navigator: Navigator = get(),
    scopeManager: ScopeManager = get()
) {
    val navController = LocalNavController.current
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
        }
        composable(MapCallouts.path) {
            MapCalloutsView(getViewModel()).Screen()
        }
        navigation(
            startDestination = WeaponCharacteristicsPistol().path,
            route = WeaponCharacteristics.path,
        ) {
            composable(WeaponCharacteristicsPistol().path) {
                scopeManager.getScopedInstance(
                    ScopeName.WEAPON_CHARACTERISTICS_SCOPE,
                    WeaponCharacteristicsViewModel::class
                ).let { vm -> WeaponCharacteristicsView(vm).Screen() }
            }
            composable(WeaponCharacteristicsHeavy().path) {
                scopeManager.getScopedInstance(
                    ScopeName.WEAPON_CHARACTERISTICS_SCOPE,
                    WeaponCharacteristicsViewModel::class
                ).let { vm -> WeaponCharacteristicsView(vm).Screen() }
            }
            composable(WeaponCharacteristicsSMG().path) {
                scopeManager.getScopedInstance(
                    ScopeName.WEAPON_CHARACTERISTICS_SCOPE,
                    WeaponCharacteristicsViewModel::class
                ).let { vm -> WeaponCharacteristicsView(vm).Screen() }
            }
            composable(WeaponCharacteristicsRifle().path) {
                scopeManager.getScopedInstance(
                    ScopeName.WEAPON_CHARACTERISTICS_SCOPE,
                    WeaponCharacteristicsViewModel::class
                ).let { vm -> WeaponCharacteristicsView(vm).Screen() }
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