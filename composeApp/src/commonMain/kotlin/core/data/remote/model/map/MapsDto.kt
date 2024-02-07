package core.data.remote.model.map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapsDto(
    @SerialName("data")
    val maps: List<MapDto>,
    val status: Int
)