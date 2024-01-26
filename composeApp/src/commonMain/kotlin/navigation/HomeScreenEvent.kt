package navigation

sealed interface HomeScreenEvent {

    data class ClickOnAgent(val agentId: String): HomeScreenEvent
}