package detail.domain.repository

import core.data.remote.ValorantWs
import core.data.remote.model.agent.DataAgentDto

interface AgentDetailsRepository {

    suspend fun getAgentDetails(agentId: String): DataAgentDto
}

class AgentDetailsRepositoryImpl(private val valorantWs: ValorantWs) : AgentDetailsRepository {

    override suspend fun getAgentDetails(agentId: String): DataAgentDto {
        return valorantWs.getAgentDetails(agentId)
    }
}