package di

import detail.presentation.DetailViewModel
import home.presentation.HomeViewModel
import org.koin.dsl.module
import utils.viewModelDefinition

val ViewModelModule = module {

    viewModelDefinition { HomeViewModel(get()) }
    viewModelDefinition { DetailViewModel(get()) }
}