package home.domain.usecases

import home.domain.repository.HomeRepository

class GetAgentsUseCase(private val repository: HomeRepository) {

    suspend operator fun invoke() = repository.getAgents()
}