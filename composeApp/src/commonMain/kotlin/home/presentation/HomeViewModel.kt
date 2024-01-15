package home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class HomeViewModel: ViewModel() {

    val name by mutableStateOf("Este texto está en el viewmodel inyectado con Koin")
}