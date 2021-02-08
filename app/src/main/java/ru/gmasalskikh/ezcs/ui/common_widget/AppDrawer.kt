package ru.gmasalskikh.ezcs.ui.common_widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.fontSize10Sp
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

@Composable
fun AppDrawer(

) {
    val theme = AmbientAppTheme.current
    val logoId = "logo"
    val spacerId = "spacer"
    val menuId = "menu"
    val appVersionId = "appVersion"
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.colors.background.copy(alpha = 0.8f)),
        constraintSet = ConstraintSet {
            val logo = createRefFor(logoId)
            val spacer = createRefFor(spacerId)
            val menu = createRefFor(menuId)
            val appVersion = createRefFor(appVersionId)

            constrain(logo) {
                start.linkTo(parent.start)
                top.linkTo(parent.top, 20.dp)
                end.linkTo(parent.end)
            }
            constrain(spacer) {
                start.linkTo(parent.start)
                top.linkTo(logo.bottom, 20.dp)
                end.linkTo(parent.end)
            }
            constrain(menu) {
                start.linkTo(parent.start)
                top.linkTo(spacer.bottom, 20.dp)
                end.linkTo(parent.end)
                bottom.linkTo(appVersion.top)
            }
            constrain(appVersion) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        }
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .layoutId(logoId),
            bitmap = imageResource(id = R.drawable.logo),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
//                        .padding(top = 50.dp, bottom = 10.dp)
                .height(1.dp)
                .fillMaxWidth(0.8f)
                .background(theme.colors.primary)
                .layoutId(spacerId)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .layoutId(menuId),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
        Text(
            modifier = Modifier
                .layoutId(appVersionId)
                .padding(5.dp),
            text = "v0.1 alpha",
            color = theme.colors.onBackground.copy(alpha = 0.8f),
            fontSize = fontSize10Sp
        )
    }
}