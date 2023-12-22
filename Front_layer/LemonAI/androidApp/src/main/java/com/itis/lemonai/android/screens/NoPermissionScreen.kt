package com.itis.lemonai.android.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera

@Composable
fun NoPermissionScreen(
    onRequestPermission: () -> Unit
) {

    NoPermissionContent(
        onRequestPermission = onRequestPermission
    )
}

@Composable
private fun NoPermissionContent(
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Пожалуйста, разрешите приложению использовать камеру.")
        Button(onClick = onRequestPermission) {
            Icon(imageVector = FeatherIcons.Camera, contentDescription = "Камера")
            Text(text = "Дать разрешение")
        }
    }
}

@Preview
@Composable
private fun Preview_NoPermissionContent() {
    NoPermissionContent(
        onRequestPermission = {}
    )
}