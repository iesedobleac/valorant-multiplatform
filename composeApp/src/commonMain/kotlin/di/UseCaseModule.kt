package di

import home.domain.usecases.GetAgentDetailsUseCase
import maps.domain.usecases.GetMapsUseCase
import home.domain.usecases.GetAgentsUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    single {
        GetAgentsUseCase(get())
    }

    single {
        GetAgentDetailsUseCase(get())
    }

    single {
        GetMapsUseCase(get())
    }
}