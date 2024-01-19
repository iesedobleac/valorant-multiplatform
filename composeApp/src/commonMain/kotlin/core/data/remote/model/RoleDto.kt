package core.data.remote.model

import core.data.model.Role
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