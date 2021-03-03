package ru.gmasalskikh.ezcs.screens.splash_screen

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class SplashScreenViewState(
    @StringRes
    val appDescriptionRes: Int = R.string.app_description,
    @DrawableRes
    val appLogoRes: Int = R.drawable.logo,
    override val currentSideEffect: SideEffect = SideEffect.Data,
) : ViewState