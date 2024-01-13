package core.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AgentDto(
    val abilities: List<AbilityDto>?,
    val background: String?,
    val backgroundGradientColors: List<String>?,
    val description: String?,
    val developerName: String?,
    val displayName: String?,
    val fullPortrait: String?,
    val fullPortraitV2: String?,
    val role: RoleDto?,
    val uuid: String?,
)