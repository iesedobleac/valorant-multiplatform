package core.data.remote.model

import core.data.model.Ability
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