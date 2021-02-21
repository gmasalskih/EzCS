package ru.gmasalskikh.ezcs.ui.common_widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.ui.theme.fontSize12Sp
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
            .background(color = backgroundColor)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = message,
                fontSize = fontSize12Sp,
                color = messageColor,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = onConfirmButtonClick,
        ) {
            Text(text = confirmButtonLabel)
        }
    }
}