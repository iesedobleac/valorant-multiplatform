package home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.data.remote.model.DataDto
import detail.presentation.DetailScreen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.compose.koinInject
import utils.AGENTS_ENDPOINT

class HomeScreen : Screen {


    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent(viewModel: HomeViewModel = koinInject<HomeViewModel>()) {

    val json = Json { ignoreUnknownKeys = true }

    val navigator = LocalNavigator.currentOrThrow
    var agentName by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    val client = HttpClient()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            val response = client.get(AGENTS_ENDPOINT)
            val body = response.bodyAsText()
            val agent = json.decodeFromString<DataDto>(body)

            agent.agent?.let { safeAgent ->
                agentName = safeAgent.displayName.orEmpty()
                image = safeAgent.fullPortrait.orEmpty()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = viewModel.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Home screen", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Agent name: $agentName")

        Spacer(modifier = Modifier.height(20.dp))

        KamelImage(
            resource = asyncPainterResource(image),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navigator.push(DetailScreen())
        }) {
            Text("Go to detail")
        }
    }
}