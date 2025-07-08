package pl.senordeveloper.a2025template.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Header(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Schedule 1:1"
    )
}