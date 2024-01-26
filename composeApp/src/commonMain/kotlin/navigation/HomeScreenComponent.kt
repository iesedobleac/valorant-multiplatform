package navigation

import com.arkivanov.decompose.ComponentContext

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val navigateToDetail: (String) -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ClickOnAgent -> {
                navigateToDetail(event.agentId)
            }
        }
    }
}