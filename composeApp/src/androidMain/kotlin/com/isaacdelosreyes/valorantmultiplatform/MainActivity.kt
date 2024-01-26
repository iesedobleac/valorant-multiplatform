package com.isaacdelosreyes.valorantmultiplatform

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import navigation.RootComponent

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = retainedComponent { RootComponent(it) }

        setContent {
            App(rootComponent = rootComponent)
        }
    }
}