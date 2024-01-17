package di

import core.data.remote.ValorantWs
import core.data.remote.ValorantWsImpl
import org.koin.dsl.module

val RemoteModule = module {

    single<ValorantWs> {
        ValorantWsImpl()
    }
}