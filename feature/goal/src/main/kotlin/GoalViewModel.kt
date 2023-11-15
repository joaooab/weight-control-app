import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.bmi.domain.calculateRecommendation
import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.model.ActionState
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.ui.input.WeightInputHandler
import com.br.weightcontrol.ui.input.isValidWeight
import com.br.weightcontrol.util.todayAsString
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GoalViewModel(
    private val handle: SavedStateHandle,
    private val goalRepository: GoalRepository,
    trackRepository: TrackRepository,
    userRepository: UserRepository
) : ViewModel() {

    val saveActionState = mutableStateOf<ActionState>(ActionState.Start)
    val weight = handle.getStateFlow(WEIGHT, InputWrapper())
    val areInputsValid = weight.map { it.isValidWeight() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )
    val currentTrack = trackRepository.getLastStream().filterNotNull().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Track()
    )
    val recommendation = userRepository.stream.map { user ->
        user?.let { calculateRecommendation(it) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null
    )

    fun onWeightEntered(input: String) {
        handle[WEIGHT] = WeightInputHandler.onInputEntered(input)
    }

    fun save() {
        viewModelScope.launch {
            createGoal()
                .onSuccess {
                    goalRepository.insert(it)
                    saveActionState.value = ActionState.Success
                }.onFailure {
                    saveActionState.value = ActionState.Failure
                }
        }
    }


    private fun createGoal() = runCatching {
        Goal(
            start = currentTrack.value.weight,
            desire = weight.value.input.toDouble(),
            createdAt = todayAsString(),
        )
    }

    fun clearSaveAction() {
        saveActionState.value = ActionState.Start
    }

    companion object {
        private const val WEIGHT = "weight"
    }
}