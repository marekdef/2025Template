package pl.senordeveloper.a2025template.model

import pl.senordeveloper.a2025template.ui.DeveloperInfo

typealias DeveloperInfoState = AState<DeveloperInfoModel, DeveloperInfoModel.Lambdas>

public val DeveloperInfoState.profileUrl: String
    get() = model.developerInfo.profileUrl

public val DeveloperInfoState.isChecked: Boolean
    get() = model.isChecked

public val DeveloperInfoState.name: String
    get() = model.developerInfo.name

public val DeveloperInfoState.developerInfo: DeveloperInfo
    get() = model.developerInfo

public fun DeveloperInfoState.onDeveloperInfoCheckedChange(checked: Boolean): DeveloperInfoState =
    copy(
        model = model.copy(
            isChecked = checked
        )
    )

public fun DeveloperInfoState.onDeveloperInfoProfileClick(onDeveloperInfoProfileClick: (DeveloperInfo) -> Unit): () -> Unit = {
    onDeveloperInfoProfileClick(developerInfo)
}

