package core.data.model.agent

data class Agent(
    val abilities: List<Ability>?,
    val background: String?,
    val backgroundGradientColors: List<String>?,
    val description: String?,
    val developerName: String?,
    val displayName: String?,
    val fullPortrait: String?,
    val fullPortraitV2: String?,
    val role: Role?,
    val uuid: String?,
)