package detail.presentation

import core.data.model.agent.Ability
import core.data.model.agent.Agent

data class DetailState(
    val agent: Agent? = null,
    val selectedAbility: Ability? = null,
)