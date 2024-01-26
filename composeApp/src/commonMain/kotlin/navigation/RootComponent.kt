package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
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
                    navigation.pushNew(Configuration.DetailScreen(it))
                })
            )

            is Configuration.DetailScreen -> Child.DetailScreen(
                DetailScreenComponent(
                    agentId = config.agentId,
                    componentContext = context,
                    goBack = {
                        navigation.pop()
                    })
            )
        }
    }

    sealed class Child {

        data class HomeScreen(val homeScreenComponent: HomeScreenComponent) : Child()
        data class DetailScreen(val detailScreenComponent: DetailScreenComponent) : Child()
    }

    @Serializable
    sealed class Configuration {

        @Serializable
        data object HomeScreen : Configuration()

        @Serializable
        data class DetailScreen(val agentId: String) : Configuration()
    }
}