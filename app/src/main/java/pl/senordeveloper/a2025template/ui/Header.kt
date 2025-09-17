package pl.senordeveloper.a2025template.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics

@Composable
fun Header(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.semantics {
            heading()
            contentDescription = "Schedule 1 on 1"
        },
        text = "Schedule 1:1",
    )
}