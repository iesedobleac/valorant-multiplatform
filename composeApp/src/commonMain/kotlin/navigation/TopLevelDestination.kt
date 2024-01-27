package navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelDestination(
    val selectedIcon: ImageVector,
    val selected: Boolean,
    val openScreen: () -> Unit,
)