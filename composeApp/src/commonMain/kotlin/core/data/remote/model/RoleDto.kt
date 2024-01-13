package core.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RoleDto(
    val description: String?,
    val displayIcon: String?,
    val displayName: String?,
)