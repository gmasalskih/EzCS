package ru.gmasalskikh.ezcs.di

import android.content.Context
import android.content.SharedPreferences
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.DbxUserFilesRequests
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gmasalskikh.ezcs.di.DependencyName.*
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.NavigatorImpl
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.app_controller.AppControllerImpl
import ru.gmasalskikh.ezcs.providers.content_repository.ContentRepository
import ru.gmasalskikh.ezcs.providers.content_repository.ContentRepositoryImpl
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScopeImpl
import ru.gmasalskikh.ezcs.providers.data_repository.DataRepository
import ru.gmasalskikh.ezcs.providers.data_repository.DataRepositoryImpl
import ru.gmasalskikh.ezcs.providers.lifecycle_holder.LifecycleHolder
import ru.gmasalskikh.ezcs.providers.lifecycle_holder.LifecycleHolderImpl
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeManager
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeManagerImpl
import ru.gmasalskikh.ezcs.providers.service_provider.Mapper
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProviderImpl
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolderImpl
import ru.gmasalskikh.ezcs.screens.grenade_practice_type_of_grenade.GrenadePracticeTypeOfGrenadeViewModel
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewModel
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsViewModel
import ru.gmasalskikh.ezcs.screens.map_callouts_details.MapCalloutsDetailsViewModel
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewModel
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveViewModel
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneViewModel
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankViewModel
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanViewModel
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenViewModel
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewModel
import ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.WeaponCharacteristicsDetailsViewModel
import ru.gmasalskikh.ezcs.utils.DROPBOX_TOKEN
import ru.gmasalskikh.ezcs.utils.SHARED_PREFERENCES_NAME
import java.util.*

enum class ScopeName(
    private var _id: UUID = UUID.randomUUID()
) {
    GRENADES_PRACTICE_SCOPE,
    WEAPON_CHARACTERISTICS_SCOPE;

    val id: String
        get() = _id.toString()

    fun setNewId() {
        _id = UUID.randomUUID()
    }
}

enum class DependencyName {
    LIFECYCLE_EMITTER,
    LIFECYCLE_COLLECTOR,
    NAV_EVENT_EMITTER,
    NAV_EVENT_FLOW,
    APP_EVENT_EMITTER,
    APP_EVENT_COLLECTOR
}

val emittersModule = module {
    factory(named(LIFECYCLE_EMITTER)) { get<LifecycleHolder>().lifecycleEmitter }
    factory(named(NAV_EVENT_EMITTER)) { get<Navigator>().targetNavigationEmitter }
    factory(named(APP_EVENT_EMITTER)) { get<AppController>().appEventEmitter }
}

val collectorsModule = module {
    factory(named(LIFECYCLE_COLLECTOR)) { get<LifecycleHolder>().lifecycleCollector }
    factory(named(NAV_EVENT_FLOW)) { get<Navigator>().navEventFlow }
    factory(named(APP_EVENT_COLLECTOR)) { get<AppController>().appEventCollector }
}

val appStateModule = module {
    single<AppStateHolder> {
        AppStateHolderImpl(
            cs = get { parametersOf(Dispatchers.Main) },
            appEventEmitter = get(named(APP_EVENT_EMITTER)),
            appEventCollector = get(named(APP_EVENT_COLLECTOR))
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
            cs = get { parametersOf(Dispatchers.Main) },
        )
    }
    single<AppController> {
        AppControllerImpl(
            cs = get { parametersOf(Dispatchers.Main) },
            navEventEmitter = get(named(NAV_EVENT_EMITTER)),
            navEventFlow = get(named(NAV_EVENT_FLOW)),
            scopeManager = get()
        )
    }
    single<ScopeManager> { ScopeManagerImpl(getKoin()) }
    single { Firebase.firestore }
    single<DbxUserFilesRequests> {
        DbxClientV2(
            DbxRequestConfig.newBuilder("Admin_EzCS/2.0").build(),
            DROPBOX_TOKEN
        ).files()
    }
    factory<ContentRepository> { ContentRepositoryImpl(dropbox = get()) }
    factory<DataRepository> { DataRepositoryImpl(firestore = get()) }
    factory {
        Mapper(
            contentRepository = get(),
            cs = get<CustomCoroutineScope> { parametersOf(Dispatchers.IO) },
            context = androidContext()
        )
    }
    factory<ServiceProvider> {
        ServiceProviderImpl(
            dataRepository = get(),
            mapper = get()
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
    viewModel {
        MapCalloutsViewModel(
            serviceProvider = get(),
            appEventEmitter = get(named(APP_EVENT_EMITTER))
        )
    }

    scope(named(ScopeName.WEAPON_CHARACTERISTICS_SCOPE)) {
        scoped {
            WeaponCharacteristicsViewModel(
                serviceProvider = get(),
                appEventCollector = get(named(APP_EVENT_COLLECTOR)),
                appEventEmitter = get(named(APP_EVENT_EMITTER)),
                cs = get { parametersOf(Dispatchers.Main) }
            )
        }
    }

    scope(named(ScopeName.GRENADES_PRACTICE_SCOPE)) {
        scoped {
            GrenadePracticeTypeOfGrenadeViewModel(
                serviceProvider = get(),
                appEventCollector = get(named(APP_EVENT_COLLECTOR)),
                cs = get { parametersOf(Dispatchers.Main) }
            )
        }
    }

    viewModel { CompetitiveViewModel(serviceProvider = get()) }
    viewModel { DangerZoneViewModel(serviceProvider = get()) }
    viewModel { ProfileRankViewModel(serviceProvider = get()) }
    viewModel { WingmanViewModel(serviceProvider = get()) }
    viewModel { (mapName: String) ->
        MapCalloutsDetailsViewModel(
            mapName = mapName,
            serviceProvider = get()
        )
    }
    viewModel { (weaponName: String) ->
        WeaponCharacteristicsDetailsViewModel(
            weaponName = weaponName,
            serviceProvider = get()
        )
    }
}