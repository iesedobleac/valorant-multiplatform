package di

import detail.domain.repository.DetailsRepository
import detail.domain.repository.DetailsRepositoryImpl
import home.domain.repository.HomeRepository
import home.domain.repository.HomeRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    single<DetailsRepository> {
        DetailsRepositoryImpl(get())
    }

    single<HomeRepository> {
        HomeRepositoryImpl(get())
    }
}