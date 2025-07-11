package pl.senordeveloper.a2025template.model

import pl.senordeveloper.a2025template.ui.DeveloperInfo

data class A11yShowcaseModel(
    val developerInfoStates: DeveloperInfoStates,
) : AModel {

    data class Lambdas(
        val onScheduleClick: () -> Unit,
        val onDeveloperInfoCheckedChange: (DeveloperInfo, Boolean) -> Unit,
        val onDeveloperProfileClicked: (DeveloperInfo) -> Unit,
        val onCopyRightClick: () -> Unit = {},
    ) : ALambdas<A11yShowcaseModel> {
        val developerInfoStatesLambdas: DeveloperInfosModel.Lambdas =
            DeveloperInfosModel.Lambdas(
                onDeveloperInfoCheckedChange = onDeveloperInfoCheckedChange,
                onDeveloperProfileClicked = onDeveloperProfileClicked
            )

        companion object {
            fun create(
                onScheduleClick: () -> Unit = {},
                onDeveloperInfoCheckedChange: (DeveloperInfo, Boolean) -> Unit = { _, _ -> },
                onDeveloperProfileClicked: (DeveloperInfo) -> Unit = {},
                onCopyRightClick: () -> Unit = {}
            ) = Lambdas(
                onScheduleClick = onScheduleClick,
                onDeveloperInfoCheckedChange = onDeveloperInfoCheckedChange,
                onDeveloperProfileClicked = onDeveloperProfileClicked,
                onCopyRightClick = onCopyRightClick
            )
        }
    }

    fun onDeveloperInfoCheckedChange(developerInfo: DeveloperInfo, isChecked: Boolean) =
        copy(developerInfoStates.onDeveloperInfoCheckedChange(developerInfo, isChecked))

    fun onScheduleClick(): String =
        developerInfoStates.onScheduleClick()
}
