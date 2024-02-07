package navigation

import com.arkivanov.decompose.ComponentContext

class MapScreenComponent(
    componentContext: ComponentContext,
    private val navigateToDetail: (String) -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: MapScreenEvent) {
        when (event) {
            is MapScreenEvent.ClickOnMap -> {
                navigateToDetail(event.mapId)
            }
        }
    }
}
