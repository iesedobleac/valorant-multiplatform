package detail.domain.repository

import core.data.remote.ValorantWs
import core.data.remote.model.DataDto

interface DetailsRepository {

    suspend fun getAgentDetails(agentId: String): DataDto
}

class DetailsRepositoryImpl(private val valorantWs: ValorantWs) : DetailsRepository {

    override suspend fun getAgentDetails(agentId: String): DataDto {
        return valorantWs.getAgentDetails(agentId)
    }
}