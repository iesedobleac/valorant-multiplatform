package utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.Typeface

actual val robotoFontFamily: FontFamily = FontFamily(
    Typeface(loadCustomFont("roboto"))
)

actual val tungstenFontFamily: FontFamily = FontFamily(
    Typeface(loadCustomFont("tungsten"))
)

actual val valorantFontFamily: FontFamily = FontFamily(
    Typeface(loadCustomFont("valorant"))
)

fun loadCustomFont(name: String): Typeface {
    return Typeface.makeFromName(name, FontStyle.NORMAL)
}


