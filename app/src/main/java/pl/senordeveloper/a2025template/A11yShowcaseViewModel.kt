package pl.senordeveloper.a2025template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.senordeveloper.a2025template.model.A11yShowcaseModel
import pl.senordeveloper.a2025template.model.A11yShowcaseState
import pl.senordeveloper.a2025template.model.DeveloperInfoModel
import pl.senordeveloper.a2025template.model.DeveloperInfoState
import pl.senordeveloper.a2025template.model.DeveloperInfoStates
import pl.senordeveloper.a2025template.model.DeveloperInfosModel
import pl.senordeveloper.a2025template.model.onDeveloperInfoCheckedChange
import pl.senordeveloper.a2025template.ui.DeveloperInfo

class A11yShowcaseViewModel : ViewModel() {
    private val _a11yShowcaseStateFlow: MutableStateFlow<A11yShowcaseState> = MutableStateFlow(
        createInitialState()
    )

    val a11yShowcaseStateFlow: StateFlow<A11yShowcaseState> = _a11yShowcaseStateFlow.asStateFlow()

    private val _events = Channel<A11yShowcaseEvent>()
    val events: Flow<A11yShowcaseEvent> = _events.receiveAsFlow()

    fun onDeveloperInfoCheckedChange(developerInfo: DeveloperInfo, isChecked: Boolean) {
        _a11yShowcaseStateFlow.update { a11yShowcaseState: A11yShowcaseState ->
            a11yShowcaseState.onDeveloperInfoCheckedChange(
                developerInfo, isChecked
            )
        }
    }

    fun onScheduleClick() {
        val message = _a11yShowcaseStateFlow.value.model.onScheduleClick()
        viewModelScope.launch {
            _events.send(A11yShowcaseEvent.DisplaySnackBar(message))
        }
    }

    fun onCopyRightClick() {
        viewModelScope.launch {
            _events.send(A11yShowcaseEvent.DisplaySnackBar("Copyright © 2025"))
        }
    }

    fun onDeveloperProfileClicked(developerInfo: DeveloperInfo) {
        viewModelScope.launch {
            _events.send(A11yShowcaseEvent.OpenWebBrowser(developerInfo.profileUrl))
        }
    }

    sealed interface A11yShowcaseEvent {
        data class OpenWebBrowser(val url: String) : A11yShowcaseEvent
        data class DisplaySnackBar(val message: String) : A11yShowcaseEvent
    }

    private fun createInitialState(): A11yShowcaseState {
        val a11yShowcaseModelLambdas = A11yShowcaseModel.Lambdas.create(
            onScheduleClick = ::onScheduleClick,
            onDeveloperInfoCheckedChange = ::onDeveloperInfoCheckedChange,
            onDeveloperProfileClicked = ::onDeveloperProfileClicked,
            onCopyRightClick = ::onCopyRightClick
        )

        return A11yShowcaseState(
            model = A11yShowcaseModel(
                developerInfoStates = DeveloperInfoStates(
                    model = DeveloperInfosModel(
                        first = DeveloperInfoState(
                            lambdas = a11yShowcaseModelLambdas.developerInfoStatesLambdas.developerInfoStateLambdas,
                            model = DeveloperInfoModel(
                                developerInfo = DeveloperInfo(
                                    name = "John Doe",
                                    profileUrl = "https://example.com/johndoe"
                                ),
                                isChecked = false
                            )
                        ),
                        second = DeveloperInfoState(
                            lambdas = a11yShowcaseModelLambdas.developerInfoStatesLambdas.developerInfoStateLambdas,
                            model = DeveloperInfoModel(
                                developerInfo = DeveloperInfo(
                                    name = "Jane Doe",
                                    profileUrl = "https://example.com/johndoe"
                                ),
                                isChecked = true
                            )
                        )
                    ),
                    lambdas = a11yShowcaseModelLambdas.developerInfoStatesLambdas
                )
            ),
            lambdas = a11yShowcaseModelLambdas
        )
    }
}
