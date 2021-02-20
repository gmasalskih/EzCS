package ru.gmasalskikh.ezcs.ui.common_widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.ui.theme.fontSize8Sp

@Composable
fun ErrorScreen(
    message: String,
    backgroundColor: Color,
    messageColor: Color,
    confirmButtonLabel: String,
    onConfirmButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = message,
            fontSize = fontSize8Sp,
            color = messageColor
        )
        Button(
            onClick = onConfirmButtonClick,
        ) {
            Text(text = confirmButtonLabel)
        }
    }
}