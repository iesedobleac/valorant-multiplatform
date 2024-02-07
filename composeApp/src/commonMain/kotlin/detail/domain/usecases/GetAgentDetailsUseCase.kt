package home.domain.usecases

import core.data.remote.model.agent.toDomain
import detail.domain.repository.AgentDetailsRepository

class GetAgentDetailsUseCase(private val repository: AgentDetailsRepository) {

    suspend operator fun invoke(agentId: String) =
        repository.getAgentDetails(agentId).agent?.toDomain()
}