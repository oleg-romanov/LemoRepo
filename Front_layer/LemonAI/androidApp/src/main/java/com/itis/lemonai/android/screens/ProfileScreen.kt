package com.itis.lemonai.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itis.lemonai.android.components.LogoutDialog
import com.itis.lemonai.android.components.ProfileField
import com.itis.lemonai.android.navigation.AppRouter
import com.itis.lemonai.android.navigation.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Info
import compose.icons.feathericons.LogOut
import compose.icons.feathericons.User

@Composable
fun ProfileScreen() {
    var firstName = remember { mutableStateOf("John") }
    var lastName = remember { mutableStateOf("Doe") }
    var username =remember { mutableStateOf("johndoe") }

    var isLoggingOut = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Имя пользователя
        ProfileField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = "Имя",
            icon = FeatherIcons.Info
        )

        // Фамилия
        ProfileField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = "Фамилия",
            icon = FeatherIcons.Info
        )

        // Логин
        ProfileField(
            value = username.value,
            onValueChange = { username.value = it },
            label = "Логин",
            icon = FeatherIcons.Info
        )

        // Кнопка выхода из аккаунта
        Button(
            onClick = {
                isLoggingOut.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Icon(imageVector = FeatherIcons.LogOut, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Выйти из аккаунта", color = Color.White)
        }

        // Диалог для подтверждения выхода из аккаунта
        if (isLoggingOut.value) {
            LogoutDialog(
                onConfirm = {
                    AppRouter.navigateTo(Screen.SignUpScreen)
                    // Действие по выходу из аккаунта
                    // Например, переход на экран входа или отправка запроса на сервер
                    // Далее можно завершить текущую активити
                    // finish()
                },
                onDismiss = { isLoggingOut.value = false }
            )
        }
    }
}