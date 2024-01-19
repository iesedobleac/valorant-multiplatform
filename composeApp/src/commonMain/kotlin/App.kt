import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import home.presentation.HomeTab
import maps.presentation.MapsTab
import org.koin.compose.KoinContext
import utils.BottomNavigationBar

@Composable
fun App() {

    val tabs = listOf(HomeTab, MapsTab)

    KoinContext {

        MaterialTheme {

            TabNavigator(
                tab = HomeTab,
                tabDisposable = {

                    TabDisposable(
                        navigator = it,
                        tabs = tabs
                    )
                }
            ) {
                Scaffold(bottomBar = {
                    BottomNavigationBar()
                }, content = {
                    CurrentTab()
                })
            }
        }
    }
}