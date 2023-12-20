package com.itis.lemonai.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itis.lemonai.android.screens.CameraScreen
import com.itis.lemonai.android.screens.HistoryScreen
import com.itis.lemonai.android.screens.ProfileScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "CameraScreen"){
        composable("CameraScreen"){
            CameraScreen()
        }
        composable("HistoryScreen"){
            HistoryScreen()
        }
        composable("ProfileScreen"){
            ProfileScreen()
        }
    }
}