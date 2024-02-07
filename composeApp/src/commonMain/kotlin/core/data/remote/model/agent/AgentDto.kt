package core.data.remote.model.agent

import core.data.model.agent.Agent
import core.data.model.agent.Role
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

fun AgentDto.toDomain() = Agent(
    abilities = abilities?.map { it.toDomain() }.orEmpty(),
    background = background.orEmpty(),
    backgroundGradientColors = backgroundGradientColors.orEmpty(),
    description = description.orEmpty(),
    developerName = developerName.orEmpty(),
    displayName = displayName.orEmpty(),
    fullPortrait = fullPortrait.orEmpty(),
    fullPortraitV2 = fullPortraitV2.orEmpty(),
    role = role?.toDomain() ?: Role(displayIcon = "", displayName = ""),
    uuid = uuid.orEmpty(),
)