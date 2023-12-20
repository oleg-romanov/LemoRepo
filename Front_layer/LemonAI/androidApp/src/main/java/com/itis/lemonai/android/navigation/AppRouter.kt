package com.itis.lemonai.android.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignUpScreen : Screen()
    object SignInScreen : Screen()
    object HistoryScreen : Screen()
}


object AppRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)
    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}