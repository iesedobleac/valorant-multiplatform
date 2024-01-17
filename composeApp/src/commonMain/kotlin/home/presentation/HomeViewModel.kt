package home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import home.domain.usecases.GetAgentByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getAgentByIdUseCase: GetAgentByIdUseCase) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getAgentByIdUseCase(agentId = "e370fa57-4757-3604-3648-499e1f642d3f")
            val agentName = data.agent?.displayName.orEmpty()
            val agentImage = data.agent?.fullPortrait.orEmpty()

            withContext(Dispatchers.Main) {
                state = state.copy(
                    agentName = agentName,
                    agentImage = agentImage
                )
            }
        }
    }
}