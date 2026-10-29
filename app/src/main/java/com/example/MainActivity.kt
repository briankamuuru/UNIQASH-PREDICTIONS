package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ui.navigation.MainAppContainer
import com.example.ui.theme.UniQashBgClean
import com.example.ui.theme.UniQashTheme
import com.example.viewmodel.PredictionsViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: PredictionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniQashTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = UniQashBgClean
                ) {
                    MainAppContainer(viewModel = viewModel)
                }
            }
        }
    }
}
