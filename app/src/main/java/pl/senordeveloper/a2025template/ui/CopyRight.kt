package pl.senordeveloper.a2025template.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CopyRight(
    modifier: Modifier = Modifier,
    onUrlClicked: () -> Unit = {}
) {
    Column {
        Text(
            modifier = modifier.fillMaxWidth().padding(8.dp).semantics() {
                role = Role.Button
                onClick {
                    onUrlClicked()
                    true
                }
            },
            textAlign = TextAlign.Center,
            text = clickableText("© 2025 Senor Developer", "Senor Developer", "https://senordeveloper.pl", onClick = onUrlClicked),
        )
        val text = clickableText2("© 2025 Marek Defecinski", "Marek Defecinski", "https://senordeveloper.pl", onClick = onUrlClicked)
        ClickableText(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            style = TextStyle.Default.copy(textAlign = TextAlign.Center),
            text = text,
        ) {
            offset ->
            val annotations = text.getStringAnnotations(tag = "url", start = offset, end = offset)
            annotations.firstOrNull()?.let { onUrlClicked() }
        }
    }


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
                        fontWeight = FontWeight.SemiBold
                    ),
                    hoveredStyle = SpanStyle(
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    ),
                    pressedStyle = SpanStyle(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.LineThrough
                    ),
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

@OptIn(ExperimentalTextApi::class)
fun clickableText2(string: String, clickablePart: String, url: String, onClick: () -> Unit): AnnotatedString {
    val startIndex = string.indexOf(clickablePart)
    val endIndex = startIndex + clickablePart.length

    val prefix = string.substring(0, startIndex)
    val suffix = string.substring(endIndex)

    val annotatedText = buildAnnotatedString {
        append(prefix)

        withAnnotation(tag = "url", annotation = url) {
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(clickablePart)
            }
        }

        append(suffix)
    }
    return annotatedText
}

@Preview
@Composable
private fun CopyRightPreview() {
    A11yPreviewBox {
        CopyRight(modifier = it )
    }
}
