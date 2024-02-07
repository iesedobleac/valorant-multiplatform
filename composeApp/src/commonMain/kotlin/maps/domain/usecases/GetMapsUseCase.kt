package maps.domain.usecases

import core.data.remote.model.map.toDomain
import detail.domain.repository.MapsRepository

class GetMapsUseCase(private val repository: MapsRepository) {

    suspend operator fun invoke() = repository.getMaps()
}