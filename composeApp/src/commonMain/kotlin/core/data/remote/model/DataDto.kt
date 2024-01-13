package core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDto(
    @SerialName("data")
    val agent: AgentDto?,
    val status: Int?
)