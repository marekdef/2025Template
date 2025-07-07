package pl.senordeveloper.a2025template.model

import pl.senordeveloper.a2025template.ui.DeveloperInfo

typealias A11yShowcaseState = AState<A11yShowcaseModel, A11yShowcaseModel.Lambdas>

val A11yShowcaseState.developerInfoStates
    get() = model.developerInfoStates

val A11yShowcaseState.isScheduleEnabled: Boolean
    get() = developerInfoStates.bothChecked


fun A11yShowcaseState.onDeveloperInfoCheckedChange(
    developerInfo: DeveloperInfo,
    isChecked: Boolean
): A11yShowcaseState = copy(
    model = model.onDeveloperInfoCheckedChange(developerInfo, isChecked)
)