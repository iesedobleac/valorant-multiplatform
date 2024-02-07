package home.domain.repository

import core.data.remote.ValorantWs
import core.data.remote.model.agent.AgentsDto

interface HomeRepository {

    suspend fun getAgents(): AgentsDto
}

class HomeRepositoryImpl(private val valorantWs: ValorantWs): HomeRepository {

    override suspend fun getAgents(): AgentsDto {
        return valorantWs.getAgents()
    }
}