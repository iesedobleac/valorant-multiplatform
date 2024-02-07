package di

import detail.presentation.DetailViewModel
import home.presentation.HomeViewModel
import maps.presentation.MapsViewModel
import org.koin.dsl.module
import utils.viewModelDefinition

val ViewModelModule = module {

    viewModelDefinition { HomeViewModel(get()) }
    viewModelDefinition { DetailViewModel(get()) }
    viewModelDefinition { MapsViewModel(get()) }
}