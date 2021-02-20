package ru.gmasalskikh.ezcs.screens.splash_screen

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.ViewState

@Parcelize
data class SplashScreenViewState(
    @StringRes
    val appDescriptionRes: Int = R.string.app_description,
    @DrawableRes
    val appLogoRes: Int = R.drawable.logo,
    val count:Int = 0
) : ViewState, Parcelable