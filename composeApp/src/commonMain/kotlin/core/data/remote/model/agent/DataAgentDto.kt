package core.data.remote.model.agent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataAgentDto(
    @SerialName("data")
    val agent: AgentDto?,
    val status: Int?
)