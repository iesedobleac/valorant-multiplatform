package home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import navigation.HomeScreenComponent
import navigation.HomeScreenEvent
import org.koin.compose.koinInject
import theme.BlackGray
import theme.Cyprus
import utils.robotoFontFamily
import utils.tungstenFontFamily

@Composable
fun HomeScreen(
    component: HomeScreenComponent,
    viewModel: HomeViewModel = koinInject<HomeViewModel>()
) {

    val state = viewModel.state

    Column(modifier = Modifier.background(BlackGray)) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1f)
        ) {

            items(state.agents) {

                val colors = it.backgroundGradientColors?.map { color ->
                    val colorParser = color.toLong(radix = 16)
                    Color(colorParser)
                }.orEmpty()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable {
                            component.onEvent(
                                HomeScreenEvent.ClickOnAgent(
                                    it.uuid.orEmpty()
                                )
                            )
                        }
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 60.dp)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 10.dp,
                                    topStart = 10.dp
                                )
                            )
                            .background(Brush.verticalGradient(colors = colors))
                    )

                    KamelImage(
                        resource = asyncPainterResource(it.fullPortraitV2.orEmpty()),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                )
                            )
                            .background(Cyprus)
                            .align(BottomCenter)
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = it.displayName.orEmpty(),
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = tungstenFontFamily,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = it.developerName.orEmpty(),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = robotoFontFamily,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}