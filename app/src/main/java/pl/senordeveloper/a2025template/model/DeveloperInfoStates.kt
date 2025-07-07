package pl.senordeveloper.a2025template.model

import pl.senordeveloper.a2025template.ui.DeveloperInfo

typealias DeveloperInfoStates = AState<DeveloperInfosModel, DeveloperInfosModel.Lambdas>

val DeveloperInfoStates.firstDeveloperInfoState: DeveloperInfoState
    get() = model.first

val DeveloperInfoStates.secondDeveloperInfoState: DeveloperInfoState
    get() = model.second

val DeveloperInfoStates.bothChecked: Boolean
    get() = model.bothChecked

fun DeveloperInfoStates.onDeveloperInfoCheckedChange(developerInfo: DeveloperInfo, isChecked: Boolean) =
    copy(model.onDeveloperInfoCheckedChange(developerInfo, isChecked))

fun DeveloperInfoStates.onScheduleClick(): String =
    model.onScheduleClick()