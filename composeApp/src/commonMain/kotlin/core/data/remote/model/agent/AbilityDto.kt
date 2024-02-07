package core.data.remote.model.agent

import core.data.model.agent.Ability
import kotlinx.serialization.Serializable

@Serializable
data class AbilityDto(
    val description: String?,
    val displayIcon: String?,
    val displayName: String?,
)

fun AbilityDto.toDomain() = Ability(
    description = description.orEmpty(),
    displayIcon = displayIcon.orEmpty(),
    displayName = displayName.orEmpty(),
)