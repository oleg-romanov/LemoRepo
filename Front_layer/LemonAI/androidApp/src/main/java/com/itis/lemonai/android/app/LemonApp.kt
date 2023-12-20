package com.itis.lemonai.android.app

import com.itis.lemonai.android.screens.HistoryScreen
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.itis.lemonai.android.screens.SignInScreen
import com.itis.lemonai.android.screens.SignUpScreen
import com.itis.lemonai.android.navigation.AppRouter
import com.itis.lemonai.android.navigation.Screen
import com.itis.lemonai.android.screens.CameraScreen
import com.itis.lemonai.android.screens.MainScreen
import com.itis.lemonai.android.screens.ProfileScreen

@Composable
fun LemonApp() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.MainScreen -> {
                    MainScreen()
                }

                is Screen.SignInScreen -> {
                    SignInScreen()
                }

                is Screen.HistoryScreen -> {
                    HistoryScreen()
                }

                is Screen.CameraScreen -> {
                    CameraScreen()
                }

                is Screen.ProfileScreen -> {
                    ProfileScreen()
                }
            }
        }

    }
}