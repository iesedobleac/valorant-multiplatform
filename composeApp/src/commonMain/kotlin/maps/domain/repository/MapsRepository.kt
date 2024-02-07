package detail.domain.repository

import core.data.remote.ValorantWs
import core.data.remote.model.map.MapsDto

interface MapsRepository {

    suspend fun getMaps(): MapsDto
}

class MapsRepositoryImpl(
    private val valorantWs: ValorantWs
) : MapsRepository {

    override suspend fun getMaps(): MapsDto {
        return valorantWs.getMaps()
    }
}