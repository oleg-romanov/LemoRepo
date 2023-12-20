package com.itis.lemonai.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itis.lemonai.android.components.ButtonComponent
import com.itis.lemonai.android.components.ClickableLoginTextComponent
import com.itis.lemonai.android.components.DividerTextComponent
import com.itis.lemonai.android.components.HeadingTextComponent
import com.itis.lemonai.android.components.MyTextFieldComponent
import com.itis.lemonai.android.components.NormalTextComponent
import com.itis.lemonai.android.components.PasswordTextFieldComponent
import com.itis.lemonai.android.navigation.AppRouter
import com.itis.lemonai.android.navigation.Screen

@Composable
fun SignUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    )
    {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = "Доброго времени суток,")
            HeadingTextComponent(value = "Создайтe аккаунт")

            MyTextFieldComponent(labelValue = "Имя")
            MyTextFieldComponent(labelValue = "Фамилия")
            MyTextFieldComponent(labelValue = "Логин")
            PasswordTextFieldComponent(labelValue = "Пароль")
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent("Зарегестрироваться", onButtonClicked = { AppRouter.navigateTo(Screen.HistoryScreen) })
            DividerTextComponent()
            ClickableLoginTextComponent(onTextSelected = { AppRouter.navigateTo(Screen.SignInScreen) })
        }
    }
}