import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import detail.presentation.DetailScreen
import home.presentation.HomeScreen
import navigation.RootComponent
import org.koin.compose.KoinContext

@Composable
fun App(rootComponent: RootComponent) {

    KoinContext {
        MaterialTheme {

            val childStack by rootComponent.childStack.subscribeAsState()

            Children(stack = childStack, animation = stackAnimation(slide())) { child ->
                when (val instance = child.instance) {
                    is RootComponent.Child.DetailScreen -> {
                        DetailScreen(instance.detailScreenComponent)
                    }

                    is RootComponent.Child.HomeScreen -> {
                        HomeScreen(instance.homeScreenComponent)
                    }
                }
            }
        }
    }
}