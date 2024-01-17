package home.domain.repository

import core.data.remote.ValorantWs
import core.data.remote.model.DataDto

interface HomeRepository {

    suspend fun getAgentById(agentId: String): DataDto

}
class HomeRepositoryImpl(private val valorantWs: ValorantWs): HomeRepository {

    override suspend fun getAgentById(agentId: String): DataDto {
        return valorantWs.getAgentById(agentId)
    }
}