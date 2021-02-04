package ru.gmasalskikh.ezcs.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.core.content.res.ResourcesCompat
import java.io.InputStream

@Composable
fun drawableFromResources(@DrawableRes id: Int): Drawable = ResourcesCompat.getDrawable(
    AmbientContext.current.resources,
    id,
    null
) ?: throw Resources.NotFoundException()

@Composable
fun bitmapFromResources(@DrawableRes id: Int): Bitmap = BitmapFactory.decodeResource(
    AmbientContext.current.resources,
    id
)

@Composable
fun bitmapFromInputStream(inputStream: InputStream): Bitmap =
    BitmapFactory.decodeStream(inputStream)


