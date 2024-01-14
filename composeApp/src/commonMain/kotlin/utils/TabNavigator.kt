package cafe.adriel.voyager.navigator.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.DisposableEffectIgnoringConfiguration
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.navigator.compositionUniqueId
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.Tab

typealias TabNavigatorContent = @Composable (tabNavigator: TabNavigator) -> Unit

val LocalTabNavigator: ProvidableCompositionLocal<TabNavigator> =
    staticCompositionLocalOf { error("TabNavigator not initialized") }

@OptIn(InternalVoyagerApi::class)
@Composable
fun TabNavigator(
    tab: Tab,
    disposeNestedNavigators: Boolean = false,
    disposeSteps: Boolean = false,
    tabDisposable: (@Composable (TabNavigator) -> Unit)? = null,
    key: String = compositionUniqueId(),
    content: TabNavigatorContent = { CurrentTab() }
) {
    Navigator(
        screen = tab,
        disposeBehavior = NavigatorDisposeBehavior(
            disposeNestedNavigators = disposeNestedNavigators,
            disposeSteps = disposeSteps
        ),
        onBackPressed = null,
        key = key
    ) { navigator ->
        val tabNavigator = remember(navigator) {
            TabNavigator(navigator)
        }

        tabDisposable?.invoke(tabNavigator)

        CompositionLocalProvider(LocalTabNavigator provides tabNavigator) {
            content(tabNavigator)
        }
    }
}

@OptIn(InternalVoyagerApi::class)
@Composable
fun TabDisposable(navigator: TabNavigator, tabs: List<Tab>) {
    DisposableEffectIgnoringConfiguration(Unit) {
        onDispose {
            tabs.forEach {
                navigator.navigator.dispose(it)
            }
        }
    }
}

class TabNavigator internal constructor(
    internal val navigator: Navigator
) {

    var current: Tab
        get() = navigator.lastItem as Tab
        set(tab) = navigator.replaceAll(tab)

    @Composable
    fun saveableState(
        key: String,
        tab: Tab = current,
        content: @Composable () -> Unit
    ) {
        navigator.saveableState(key, tab, content = content)
    }
}
