package di

import home.presentation.HomeViewModel
import org.koin.dsl.module

val SharedModule = module {

    single {
        HomeViewModel()
    }
}