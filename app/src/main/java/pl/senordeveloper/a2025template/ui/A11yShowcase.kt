package pl.senordeveloper.a2025template.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.senordeveloper.a2025template.model.A11yShowcaseModel
import pl.senordeveloper.a2025template.model.A11yShowcaseState
import pl.senordeveloper.a2025template.model.DeveloperInfoModel
import pl.senordeveloper.a2025template.model.DeveloperInfoState
import pl.senordeveloper.a2025template.model.DeveloperInfoStates
import pl.senordeveloper.a2025template.model.DeveloperInfosModel
import pl.senordeveloper.a2025template.model.developerInfoStates
import pl.senordeveloper.a2025template.model.isScheduleEnabled

@Composable
fun A11yShowcase(
    modifier: Modifier = Modifier,
    a11yShowcaseState: A11yShowcaseState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Header(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        DeveloperInfos(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            a11yShowcaseState.developerInfoStates
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            onClick = a11yShowcaseState.lambdas.onScheduleClick,
            enabled = a11yShowcaseState.isScheduleEnabled
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Schedule"
            )
        }
        CopyRight(
            modifier = Modifier.padding(8.dp),
            onUrlClicked = a11yShowcaseState.lambdas.onCopyRightClick
        )
    }
}

@Preview
@Composable
private fun A11yShowcasePreview() {
    A11yPreviewBox { modifier ->
        A11yShowcase(
            modifier = modifier,
            a11yShowcaseState = A11yShowcaseState(
                model = A11yShowcaseModel(
                    developerInfoStates = DeveloperInfoStates(
                        model = DeveloperInfosModel(
                            first = DeveloperInfoState(
                                lambdas = DeveloperInfoModel.Lambdas.create(),
                                model = DeveloperInfoModel(
                                    developerInfo = DeveloperInfo(
                                        name = "John Doe",
                                        profileUrl = "https://example.com/johndoe"
                                    ),
                                    isChecked = false
                                )
                            ),
                            second = DeveloperInfoState(
                                lambdas = DeveloperInfoModel.Lambdas.create(),
                                model = DeveloperInfoModel(
                                    developerInfo = DeveloperInfo(
                                        name = "Jane Doe",
                                        profileUrl = "https://example.com/johndoe"
                                    ),
                                    isChecked = true
                                )
                            )
                        ),
                        lambdas = DeveloperInfosModel.Lambdas.create()
                    )
                ),
                lambdas = A11yShowcaseModel.Lambdas.create()
            )
        )
    }
}
