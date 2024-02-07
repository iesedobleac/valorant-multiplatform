package maps.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.data.remote.model.map.toDomain
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import maps.domain.usecases.GetMapsUseCase

class MapsViewModel(private val getMapsUseCase: GetMapsUseCase) : ViewModel() {

    var state by mutableStateOf(MapsState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getMapsUseCase()

            withContext(Dispatchers.Main) {
                if (data.maps.isNotEmpty()) {
                    state = state.copy(
                        maps = data.maps.map { it.toDomain() }
                    )
                }
            }
        }
    }
}