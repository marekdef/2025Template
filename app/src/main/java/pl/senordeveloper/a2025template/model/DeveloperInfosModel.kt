package pl.senordeveloper.a2025template.model

import pl.senordeveloper.a2025template.ui.DeveloperInfo

data class DeveloperInfosModel(
    val first: DeveloperInfoState,
    val second: DeveloperInfoState,
) : AModel {
    fun onDeveloperInfoCheckedChange(
        developerInfo: DeveloperInfo,
        checked: Boolean
    ): DeveloperInfosModel =
        when (developerInfo) {
            first.developerInfo -> copy(
                first = first.onDeveloperInfoCheckedChange(checked)
            )

            second.developerInfo -> copy(
                second = second.onDeveloperInfoCheckedChange(checked)
            )

            else -> throw IllegalStateException(
                "DeveloperInfo $developerInfo not found in DeveloperInfoStates"
            )
        }

    fun onScheduleClick(): String {
        assert(bothChecked)
        return "${first.developerInfo.name} & ${second.developerInfo.name} will have 1:1"
    }

    val bothChecked: Boolean = first.isChecked
        && second.isChecked



    data class Lambdas(
        val onDeveloperInfoCheckedChange: (DeveloperInfo, Boolean) -> Unit,
        val onDeveloperProfileClicked: (DeveloperInfo) -> Unit
    ) : ALambdas<DeveloperInfosModel> {
        val developerInfoStateLambdas: DeveloperInfoModel.Lambdas =
            DeveloperInfoModel.Lambdas(
                onDeveloperInfoCheckedChange = onDeveloperInfoCheckedChange,
                onDeveloperInfoProfileClick = onDeveloperProfileClicked
            )

        companion object Companion {
            fun create(
                onDeveloperInfoCheckedChange: (DeveloperInfo, Boolean) -> Unit = { _, _ -> },
                onDeveloperProfileClicked: (DeveloperInfo) -> Unit = { }
            ) = Lambdas(
                onDeveloperInfoCheckedChange = onDeveloperInfoCheckedChange,
                onDeveloperProfileClicked = onDeveloperProfileClicked
            )
        }
    }



}
