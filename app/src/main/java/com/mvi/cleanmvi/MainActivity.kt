package com.mvi.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.Surface
import com.mvi.presentation.contract.splash.SplashContract
import com.mvi.presentation.viewmodel.splash.SplashViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        viewModel.setEvent(SplashContract.Event.Close)

        splashScreen.setKeepOnScreenCondition { viewModel.viewState.value is SplashContract.State.Loading }
        enableEdgeToEdge()
        setContent {
            CleanMVITheme {
                Surface(color = MaterialTheme.colorScheme.primary) { AppNavigation() }
            }
        }
    }
}
