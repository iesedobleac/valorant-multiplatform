package di

import home.domain.usecases.GetAgentDetailsUseCase
import home.domain.usecases.GetAgentsUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    single {
        GetAgentsUseCase(get())
    }

    single {
        GetAgentDetailsUseCase(get())
    }
}