package pl.senordeveloper.a2025template.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CopyRight(
    modifier: Modifier = Modifier,
    onUrlClicked: () -> Unit = {}
) {
    Text(
        modifier = modifier.fillMaxWidth().semantics() {
            role = Role.Button
            onClick {
                onUrlClicked()
                true
            }
        },
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = Color.Blue
        ),
        text = clickableText("© 2025 Senor Developer", "Senor Developer", "https://senordeveloper.pl", onClick = onUrlClicked),
    )
}

fun clickableText(string: String, clickablePart: String, url: String, onClick: () -> Unit): AnnotatedString {
    val startIndex = string.indexOf(clickablePart)
    val endIndex = startIndex + clickablePart.length

    val prefix = string.substring(0, startIndex)
    val suffix = string.substring(endIndex)

    return buildAnnotatedString {
        append(prefix)
        withLink(
            LinkAnnotation.Clickable(
                tag = "clickable_text",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    ),
                    focusedStyle = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = {
                    onClick()
                }
            )) {
            append(clickablePart)
        }
        append(suffix)
    }
}

@Preview
@Composable
private fun CopyRightPreview() {
    A11yPreviewBox {
        CopyRight(modifier = it )
    }
}
