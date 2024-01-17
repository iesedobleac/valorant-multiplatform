import androidx.compose.ui.window.ComposeUIViewController
import di.RemoteModule
import di.RepositoryModule
import di.UseCaseModule
import di.ViewModelModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

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