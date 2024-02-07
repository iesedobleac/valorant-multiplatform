package core.data.remote.model.agent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentsDto(
    @SerialName("data")
    val agents: List<AgentDto>?,
    val status: Int?
)