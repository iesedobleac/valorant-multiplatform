package home.domain.usecases

import core.data.remote.model.DataDto
import home.domain.repository.HomeRepository

class GetAgentByIdUseCase(private val homeRepository: HomeRepository) {

    suspend operator fun invoke(agentId: String): DataDto {
        return homeRepository.getAgentById(agentId)
    }
}