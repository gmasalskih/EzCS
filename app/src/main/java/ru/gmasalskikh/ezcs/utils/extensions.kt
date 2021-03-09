package ru.gmasalskikh.ezcs.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import java.io.InputStream
import java.util.*

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

fun bitmapFromInputStream(inputStream: InputStream): Bitmap =
    BitmapFactory.decodeStream(inputStream)

@Composable
inline fun NavController.CurrentRoute(callBack: (String) -> Unit) {
    val navBackStackEntry by currentBackStackEntryAsState()
    navBackStackEntry?.arguments?.getString(KEY_ROUTE)?.let { currentPath ->
        callBack(currentPath)
    }
}

fun String.toValidId() = this.replace("[^a-zA-Z0-9_\\s]".toRegex(), "")
    .replace("[\\s]+".toRegex(), "_")
    .replace("[_]+".toRegex(), "_")
    .toLowerCase(Locale.getDefault())


