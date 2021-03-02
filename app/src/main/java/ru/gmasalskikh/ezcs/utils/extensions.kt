package ru.gmasalskikh.ezcs.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import org.koin.androidx.viewmodel.ViewModelOwner.Companion.from
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import java.io.InputStream

@Composable
fun drawableFromResources(@DrawableRes id: Int): Drawable = ResourcesCompat.getDrawable(
    LocalContext.current.resources,
    id,
    null
) ?: throw Resources.NotFoundException()

@Composable
fun bitmapFromResources(@DrawableRes id: Int): Bitmap = BitmapFactory.decodeResource(
    LocalContext.current.resources,
    id
)

@Composable
fun bitmapFromInputStream(inputStream: InputStream): Bitmap =
    BitmapFactory.decodeStream(inputStream)

@Composable
inline fun NavController.CurrentRoute(callBack: (String) -> Unit) {
    val navBackStackEntry by currentBackStackEntryAsState()
    navBackStackEntry?.arguments?.getString(KEY_ROUTE)?.let { currentPath ->
        callBack(currentPath)
    }
}

@Composable
inline fun <reified T : ViewModel> getViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val owner = LocalViewModelStoreOwner.current.viewModelStore
    return remember {
        GlobalContext.get()
            .getViewModel(qualifier, owner = { from(owner) }, parameters = parameters)
    }
}

@Composable
inline fun <reified T> get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T = remember {
    GlobalContext.get().get(qualifier, parameters)
}

@Composable
fun getKoin(): Koin = remember {
    GlobalContext.get()
}


