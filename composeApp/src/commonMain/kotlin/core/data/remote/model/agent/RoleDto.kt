package core.data.remote.model.agent

import core.data.model.agent.Role
import kotlinx.serialization.Serializable

@Serializable
data class RoleDto(
    val description: String?,
    val displayIcon: String?,
    val displayName: String?,
)

fun RoleDto.toDomain() = Role(
    displayIcon = displayIcon.orEmpty(),
    displayName = displayName.orEmpty(),
)