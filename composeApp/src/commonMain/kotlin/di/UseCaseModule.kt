package di

import home.domain.usecases.GetAgentByIdUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    single {
        GetAgentByIdUseCase(get())
    }
}