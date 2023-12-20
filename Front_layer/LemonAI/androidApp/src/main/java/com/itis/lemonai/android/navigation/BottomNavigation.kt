package com.itis.lemonai.android.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(
    navController: NavController
) {
    val listItems = listOf(
        BottomItem.ScreenCamera,
        BottomItem.ScreenHistory,
        BottomItem.ScreenProfile
    )
    NavigationBar(Modifier.background(Color.White),
        containerColor = Color.White) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRout = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.imageVector, contentDescription = "Icon") },
                label = { Text(item.title) },
                selected = currentRout == item.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
    }

    }
}