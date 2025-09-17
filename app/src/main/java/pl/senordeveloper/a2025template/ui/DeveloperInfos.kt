package pl.senordeveloper.a2025template.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.senordeveloper.a2025template.model.DeveloperInfoModel
import pl.senordeveloper.a2025template.model.DeveloperInfoState
import pl.senordeveloper.a2025template.model.DeveloperInfoStates
import pl.senordeveloper.a2025template.model.DeveloperInfosModel
import pl.senordeveloper.a2025template.model.firstDeveloperInfoState
import pl.senordeveloper.a2025template.model.secondDeveloperInfoState

@Composable
fun DeveloperInfos(
    modifier: Modifier = Modifier,
    developerInfoStates: DeveloperInfoStates
) {
    Column(modifier = modifier) {
        DeveloperInfo(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            developerInfoStates.firstDeveloperInfoState
        )
        DeveloperInfo(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            developerInfoStates.secondDeveloperInfoState
        )
    }
}

@Preview
@Composable
private fun DeveloperInfosPreview() {
    A11yPreviewBox { modifier: Modifier ->
        DeveloperInfos(
            modifier = modifier,
            developerInfoStates = DeveloperInfoStates(
                model = DeveloperInfosModel(
                    first = DeveloperInfoState(
                        model = DeveloperInfoModel(
                            developerInfo = DeveloperInfo(
                                name = "John Doe",
                                profileUrl = "https://example.com/johndoe"
                            ), isChecked = false
                        ),
                        lambdas = DeveloperInfoModel.Lambdas.create()
                    ),
                    second = DeveloperInfoState(
                        model = DeveloperInfoModel(
                            developerInfo = DeveloperInfo(
                                name = "John Doe",
                                profileUrl = "https://example.com/johndoe"
                            ), isChecked = true
                        ),
                        lambdas = DeveloperInfoModel.Lambdas.create()
                    )
                ),
                lambdas = DeveloperInfosModel.Lambdas.create()
            )
        )
    }
}