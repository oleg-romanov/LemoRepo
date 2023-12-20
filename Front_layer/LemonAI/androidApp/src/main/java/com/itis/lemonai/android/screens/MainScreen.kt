package com.itis.lemonai.android.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.itis.lemonai.android.navigation.BottomNavigation
import com.itis.lemonai.android.navigation.NavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ){
        NavGraph(navHostController = navController)
    }
}