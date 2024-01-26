package detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.data.model.Ability
import core.data.model.Agent
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import home.domain.usecases.GetAgentDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val getAgentDetailsUseCase: GetAgentDetailsUseCase) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    fun getAgentDetails(agentId: String) {
        viewModelScope.launch {
            val agent = getAgentDetailsUseCase(agentId)
            agent?.let { parseAgentDataAndSetState(it) }
        }
    }

    private suspend fun parseAgentDataAndSetState(agent: Agent) {
        withContext(Dispatchers.Main) {
            state = state.copy(
                agent = agent,
                selectedAbility = agent.abilities?.get(0)
            )
        }
    }

    fun changeSelectedAbility(ability: Ability) {
        state = state.copy(
            selectedAbility = ability
        )
    }

}