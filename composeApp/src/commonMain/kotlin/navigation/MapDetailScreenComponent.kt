package navigation

import com.arkivanov.decompose.ComponentContext

class MapDetailScreenComponent(
    val mapId: String,
    componentContext: ComponentContext,
    private val goBack: () -> Unit,
) : ComponentContext by componentContext {

    fun goBack() {
        goBack.invoke()
    }
}
