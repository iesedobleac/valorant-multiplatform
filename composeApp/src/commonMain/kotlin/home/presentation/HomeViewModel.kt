package home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.data.remote.model.toDomain
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import home.domain.usecases.GetAgentsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getAgentsUseCase: GetAgentsUseCase) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getAgentsUseCase()

            withContext(Dispatchers.Main) {
                if (!data.agents.isNullOrEmpty()) {
                    state = state.copy(
                        agents = data.agents.map { it.toDomain() }
                    )
                }
            }
        }
    }
}