package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.fontSize10Sp
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

@Composable
fun AppDrawer() {
    val theme = LocalAppTheme.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.colors.background.copy(0.8f)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .height(1.dp)
                .fillMaxWidth(0.8f)
                .background(theme.colors.primary)
        )
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = "v0.1 alpha",
            color = theme.colors.onBackground.copy(alpha = 0.8f),
            fontSize = fontSize10Sp
        )
    }
}