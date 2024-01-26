package home.domain.usecases

import core.data.remote.model.toDomain
import detail.domain.repository.DetailsRepository

class GetAgentDetailsUseCase(private val repository: DetailsRepository) {

    suspend operator fun invoke(agentId: String) =
        repository.getAgentDetails(agentId).agent?.toDomain()
}