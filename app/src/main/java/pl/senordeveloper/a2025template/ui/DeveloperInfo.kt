package pl.senordeveloper.a2025template.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.senordeveloper.a2025template.model.DeveloperInfoModel
import pl.senordeveloper.a2025template.model.DeveloperInfoState
import pl.senordeveloper.a2025template.model.developerInfo
import pl.senordeveloper.a2025template.model.isChecked
import pl.senordeveloper.a2025template.model.name
import pl.senordeveloper.a2025template.model.onDeveloperInfoProfileClick
import pl.senordeveloper.a2025template.model.profileUrl


@Composable
fun DeveloperInfo(
    modifier: Modifier = Modifier,
    developerInfoState: DeveloperInfoState,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = urlText(
                "View profile",
                developerInfoState.profileUrl,
                onClick = developerInfoState.onDeveloperInfoProfileClick(developerInfoState.lambdas.onDeveloperInfoProfileClick)
            )
        )
        Row(
            modifier = Modifier.padding(8.dp).semantics(true) {
                role = Role.Switch
                contentDescription =  if (developerInfoState.isChecked) {
                    "${developerInfoState.name} 1:1 is planned"
                } else {
                    "${developerInfoState.name} 1:1 is not planned"
                }
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                modifier = Modifier,
                checked = developerInfoState.isChecked,
                onCheckedChange = developerInfoState.lambdas.onDeveloperInfoCheckedChange(developerInfoState.developerInfo)
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = developerInfoState.name
            )
        }
    }
}

fun urlText(
    text: String,
    url: String,
    onClick: () -> Unit
): AnnotatedString =
    buildAnnotatedString {
        withLink(
            LinkAnnotation.Url(
                url = url,
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = { link ->
                    onClick()
                }
            )
        ) {
            append(text)
        }
    }

@Preview
@Composable
private fun DeveloperInfoPreview() {
    A11yPreviewBox {
        DeveloperInfo(
            modifier = it,
            developerInfoState = DeveloperInfoState(
                DeveloperInfoModel(
                    developerInfo = DeveloperInfo(
                        name = "John Doe",
                        profileUrl = "https://example.com/johndoe"
                    ),
                    isChecked = true
                ),
                DeveloperInfoModel.Lambdas.create()
            )
        )
    }
}

data class DeveloperInfo(
    val name: String,
    val profileUrl: String
)