package pl.senordeveloper.a2025template

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun A11yShowcase(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun A11yPreviewBox(content: @Composable (Modifier) -> Unit) {
    Scaffold { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
private fun A11yShowcasePreview() {
    A11yPreviewBox {
        A11yShowcase("Marek", modifier = it)
    }
}
