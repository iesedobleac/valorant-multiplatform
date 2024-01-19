package utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import home.presentation.HomeTab
import maps.presentation.MapsTab

@Composable
fun BottomNavigationBar() {

    val tabs = listOf(HomeTab, MapsTab)

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color.White
    ) {
        tabs.forEach { tab ->
            TabNavigationItem(tab)
        }
    }
}