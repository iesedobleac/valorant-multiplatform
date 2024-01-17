package di

import home.domain.repository.HomeRepository
import home.domain.repository.HomeRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    single<HomeRepository> {
        HomeRepositoryImpl(get())
    }
}