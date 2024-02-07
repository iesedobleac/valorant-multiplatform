package home.presentation

import core.data.model.agent.Agent

data class HomeState(
    val agents: List<Agent> = emptyList()
)