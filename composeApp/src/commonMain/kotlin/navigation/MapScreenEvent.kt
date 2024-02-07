package navigation

sealed interface MapScreenEvent {

    data class ClickOnMap(val mapId: String): MapScreenEvent
}