package core.data.remote.model.map

import core.data.model.map.Map
import kotlinx.serialization.Serializable

@Serializable
data class MapDto(
    val coordinates: String?,
    val displayIcon: String?,
    val displayName: String?,
    val mapUrl: String?,
    val narrativeDescription: String?,
    val splash: String?,
    val uuid: String?,
)

fun MapDto.toDomain() =
    Map(
        coordinates = coordinates.orEmpty(),
        displayIcon = displayIcon.orEmpty(),
        displayName = displayName.orEmpty(),
        mapUrl = mapUrl.orEmpty(),
        narrativeDescription = narrativeDescription.orEmpty(),
        splash = splash.orEmpty(),
        uuid = uuid.orEmpty(),
    )