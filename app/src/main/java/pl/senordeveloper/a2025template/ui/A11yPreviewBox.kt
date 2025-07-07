package pl.senordeveloper.a2025template.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun A11yPreviewBox(content: @Composable (Modifier) -> Unit) {
    Scaffold { innerPadding ->
        content(Modifier.Companion.padding(innerPadding))
    }
}

@Preview
@Composable
private fun A11yPreviewBoxPreview() {
    A11yPreviewBox {
    }
}