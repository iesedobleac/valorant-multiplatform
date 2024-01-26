package detail.presentation

import core.data.model.Ability
import core.data.model.Agent

data class DetailState(
    val agent: Agent? = null,
    val selectedAbility: Ability? = null,
)