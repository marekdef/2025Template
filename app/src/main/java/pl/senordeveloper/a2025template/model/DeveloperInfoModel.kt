package pl.senordeveloper.a2025template.model

import pl.senordeveloper.a2025template.ui.DeveloperInfo

data class DeveloperInfoModel(
    val developerInfo: DeveloperInfo,
    val isChecked: Boolean
) : AModel {
    data class Lambdas(
        val onDeveloperInfoCheckedChange: (DeveloperInfo, Boolean) -> Unit,
        val onDeveloperInfoProfileClick: (DeveloperInfo) -> Unit
    ) : ALambdas<DeveloperInfoModel> {
        fun onDeveloperInfoCheckedChange(developerInfo: DeveloperInfo): (Boolean) -> Unit = { isChecked ->
            onDeveloperInfoCheckedChange(developerInfo, isChecked)
        }

        companion object Companion {
            fun create(
                onDeveloperInfoCheckedChange: (DeveloperInfo, Boolean) -> Unit = { _, _ -> },
                onDeveloperInfoProfileClick: (DeveloperInfo) -> Unit = {}
            ) = Lambdas(
                onDeveloperInfoCheckedChange = onDeveloperInfoCheckedChange,
                onDeveloperInfoProfileClick = onDeveloperInfoProfileClick
            )
        }
    }
}
