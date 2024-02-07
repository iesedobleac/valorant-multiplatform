package di

import detail.domain.repository.AgentDetailsRepository
import detail.domain.repository.AgentDetailsRepositoryImpl
import detail.domain.repository.MapsRepository
import detail.domain.repository.MapsRepositoryImpl
import home.domain.repository.HomeRepository
import home.domain.repository.HomeRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    single<AgentDetailsRepository> {
        AgentDetailsRepositoryImpl(get())
    }

    single<HomeRepository> {
        HomeRepositoryImpl(get())
    }

    single<MapsRepository> {
        MapsRepositoryImpl(get())
    }
}