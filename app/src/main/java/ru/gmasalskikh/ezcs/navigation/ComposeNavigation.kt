package ru.gmasalskikh.ezcs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.*
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.gmasalskikh.ezcs.di.ScopeName
import ru.gmasalskikh.ezcs.navigation.TargetNavigation.*
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeManager
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.GrenadePracticeDetailsView
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.GrenadePracticeDetailsViewModel
import ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.PlacesOnMapsView
import ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.PlacesOnMapsViewModel
import ru.gmasalskikh.ezcs.screens.grenade_practice.type_of_grenade.GrenadePracticeTypeOfGrenadeView
import ru.gmasalskikh.ezcs.screens.grenade_practice.type_of_grenade.GrenadePracticeTypeOfGrenadeViewModel
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsView
import ru.gmasalskikh.ezcs.screens.map_callouts_details.MapCalloutsDetailsView
import ru.gmasalskikh.ezcs.screens.map_callouts_details.MapCalloutsDetailsViewModel
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveView
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneView
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankView
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewModel
import ru.gmasalskikh.ezcs.utils.LocalNavController

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
        composable(MapCalloutsDetails().path) {
            it.arguments
                ?.getString(MapCalloutsDetailsViewModel.MAP_CALLOUTS_MAP_NAME)
                ?.let { mapName ->
                    MapCalloutsDetailsView(getViewModel { parametersOf(mapName) }).Screen()
                }
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
        navigation(
            startDestination = GrenadePracticeSmoke().path,
            route = GrenadesPractice.path
        ) {
            composable(GrenadePracticeSmoke().path) {
                scopeManager.getScopedInstance(
                    ScopeName.GRENADES_PRACTICE_SCOPE,
                    GrenadePracticeTypeOfGrenadeViewModel::class
                ).let { vm-> GrenadePracticeTypeOfGrenadeView(vm).Screen() }
            }
            composable(GrenadePracticeMolotov().path) {
                scopeManager.getScopedInstance(
                    ScopeName.GRENADES_PRACTICE_SCOPE,
                    GrenadePracticeTypeOfGrenadeViewModel::class
                ).let { vm-> GrenadePracticeTypeOfGrenadeView(vm).Screen() }
            }
            composable(GrenadePracticeFlash().path) {
                scopeManager.getScopedInstance(
                    ScopeName.GRENADES_PRACTICE_SCOPE,
                    GrenadePracticeTypeOfGrenadeViewModel::class
                ).let { vm-> GrenadePracticeTypeOfGrenadeView(vm).Screen() }
            }
            navigation(
                startDestination = TickRate64().path,
                route = TickRates().path
            ){
                composable(TickRate64().path) {
                    scopeManager.getScopedInstance(
                        ScopeName.TICKRATE_SCOPE,
                        PlacesOnMapsViewModel::class
                    ).let { vm -> PlacesOnMapsView(vm).Screen() }

//                    it.arguments
//                        ?.getString(PlacesOnMapsViewModel.GRENADE_PRACTICE_MAP_NAME)
//                        ?.let { mapName ->
//                            PlacesOnMapsView(getViewModel { parametersOf(mapName) }).Screen()
//                        }
                }
                composable(TickRate128().path) {
                    scopeManager.getScopedInstance(
                        ScopeName.TICKRATE_SCOPE,
                        PlacesOnMapsViewModel::class
                    ).let { vm -> PlacesOnMapsView(vm).Screen()}
                }
                composable(GrenadePracticeDetails().path) {
                    it.arguments
                        ?.getString(GrenadePracticeDetailsViewModel.GRENADE_PRACTICE_DETAILS_MAPPOINT_NAME)
                        ?.let { mapPointName ->
                            GrenadePracticeDetailsView(getViewModel {
                                parametersOf(mapPointName)
                            }).Screen()
                        }
                }
            }
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