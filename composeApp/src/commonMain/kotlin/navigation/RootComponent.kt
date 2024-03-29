package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        initialConfiguration = Configuration.HomeScreen,
        serializer = Configuration.serializer(),
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(config: Configuration, context: ComponentContext): Child {
        return when (config) {
            Configuration.HomeScreen -> Child.HomeScreen(
                HomeScreenComponent(componentContext = context, navigateToDetail = {
                    navigation.pushNew(Configuration.AgentDetailScreen(it))
                })
            )

            is Configuration.AgentDetailScreen -> Child.AgentDetailScreen(
                AgentDetailScreenComponent(
                    agentId = config.agentId,
                    componentContext = context,
                    goBack = {
                        navigation.pop()
                    })
            )

            Configuration.MapScreen -> Child.MapScreen(
                MapScreenComponent(
                    componentContext = context,
                    navigateToDetail = {
                        //TODO
                    })
            )

            is Configuration.MapDetailScreen -> Child.MapDetailScreen(
                MapDetailScreenComponent(
                    mapId = config.mapId,
                    componentContext = context,
                    goBack = {
                        navigation.pop()
                    }
                )
            )
        }
    }

    fun openHome() {
        navigation.bringToFront(Configuration.HomeScreen)
    }

    fun openMaps() {
        navigation.bringToFront(Configuration.MapScreen)
    }

    sealed class Child {

        data class HomeScreen(val homeScreenComponent: HomeScreenComponent) : Child()
        data class AgentDetailScreen(val agentDetailScreenComponent: AgentDetailScreenComponent) : Child()
        data class MapScreen(val mapScreenComponent: MapScreenComponent) : Child()
        data class MapDetailScreen(val mapDetailScreenComponent: MapDetailScreenComponent): Child()
    }

    @Serializable
    sealed class Configuration {

        @Serializable
        data object HomeScreen : Configuration()

        @Serializable
        data class AgentDetailScreen(val agentId: String) : Configuration()

        @Serializable
        data object MapScreen : Configuration()

        @Serializable
        data class MapDetailScreen(val mapId: String) : Configuration()
    }
}