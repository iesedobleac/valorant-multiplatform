import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import detail.presentation.DetailScreen
import home.presentation.HomeScreen
import maps.presentation.MapsScreen
import navigation.RootComponent
import navigation.TopLevelDestination
import org.koin.compose.KoinContext

@Composable
fun App(rootComponent: RootComponent) {

    val childStack by rootComponent.childStack.subscribeAsState()
    var selectedItem by remember { mutableStateOf(0) }

    val topLevelDestination by remember {
        mutableStateOf(
            listOf(
                TopLevelDestination(
                    selectedIcon = Icons.Default.Home,
                    selected = false,
                    openScreen = rootComponent::openHome
                ),
                TopLevelDestination(
                    selectedIcon = Icons.Default.Map,
                    selected = false,
                    openScreen = rootComponent::openMaps
                )
            )
        )
    }

    KoinContext {

        MaterialTheme {

            Scaffold(bottomBar = {
                if (showNavigationBar(childStack.active.instance)) {
                    BottomAppBar(modifier = Modifier.fillMaxWidth().height(60.dp), actions = {
                        topLevelDestination.forEachIndexed { index, topLevelDestination ->
                            NavigationBarItem(
                                selected = selectedItem == index,
                                icon = {
                                    Icon(
                                        imageVector = topLevelDestination.selectedIcon,
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    selectedItem = index
                                    topLevelDestination.openScreen()
                                },
                            )
                        }
                    }, containerColor = Color.White)
                }
            }, content = {
                Children(stack = childStack, animation = stackAnimation(slide())) { child ->
                    when (val instance = child.instance) {
                        is RootComponent.Child.AgentDetailScreen -> {
                            DetailScreen(instance.agentDetailScreenComponent)
                        }

                        is RootComponent.Child.HomeScreen -> {
                            HomeScreen(instance.homeScreenComponent)
                        }

                        is RootComponent.Child.MapScreen -> {
                            MapsScreen(instance.mapScreenComponent)
                        }

                        is RootComponent.Child.MapDetailScreen -> {
                            //TODO
                        }
                    }
                }
            })
        }
    }
}

@Composable
fun showNavigationBar(instance: RootComponent.Child): Boolean {
    return instance !is RootComponent.Child.AgentDetailScreen
}