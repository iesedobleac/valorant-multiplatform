import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import home.presentation.HomeScreen

@Composable
fun App() {
    Navigator(screen = HomeScreen()) { navigator ->
        SlideTransition(navigator = navigator)
    }
}