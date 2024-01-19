package home.presentation

import core.data.model.Agent

data class HomeState(
    val agents: List<Agent> = emptyList()
)