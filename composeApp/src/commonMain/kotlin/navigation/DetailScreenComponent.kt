package navigation

import com.arkivanov.decompose.ComponentContext

class DetailScreenComponent(
    val agentId: String,
    componentContext: ComponentContext,
    private val goBack: () -> Unit,
) : ComponentContext by componentContext {

    fun goBack() {
        goBack.invoke()
    }
}