import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import di.RemoteModule
import di.RepositoryModule
import di.UseCaseModule
import di.ViewModelModule
import navigation.RootComponent
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    val rootComponent = remember { RootComponent(DefaultComponentContext(LifecycleRegistry())) }

    App(rootComponent = rootComponent)
}

fun initKoin() {
    startKoin {
        modules(
            RemoteModule,
            RepositoryModule,
            UseCaseModule,
            ViewModelModule
        )
    }
}