package core.data.remote.model.map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DataMapDto(
    @SerialName("data")
    val map: MapDto,
    val status: Int
)