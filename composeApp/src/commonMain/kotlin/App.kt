import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import home.presentation.HomeTab
import maps.presentation.MapsTab
import org.koin.compose.KoinContext
import utils.TabNavigationItem

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
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = it.current.options.title)
                    })
                }, bottomBar = {
                    BottomNavigation {

                        tabs.forEach { tab ->
                            TabNavigationItem(tab)
                        }
                    }
                }, content = {
                    CurrentTab()
                })
            }
        }
    }
}