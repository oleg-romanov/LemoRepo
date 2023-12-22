package com.itis.lemonai.android.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.itis.lemonai.User
import com.itis.lemonai.UserLogin
import com.itis.lemonai.android.components.ButtonComponent
import com.itis.lemonai.android.components.ClickableLoginTextComponent
import com.itis.lemonai.android.components.DividerTextComponent
import com.itis.lemonai.android.components.HeadingTextComponent
import com.itis.lemonai.android.components.MyTextFieldComponent
import com.itis.lemonai.android.components.NormalTextComponent
import com.itis.lemonai.android.components.PasswordTextFieldComponent
import com.itis.lemonai.android.navigation.AppRouter
import com.itis.lemonai.android.navigation.Screen
import com.itis.lemonai.httpAddUser
import com.itis.lemonai.httpGetUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SignInScreen() {

    var isLoginFieldFilled by remember { mutableStateOf(false) }
    var isPasswordFieldFilled by remember { mutableStateOf(false) }

    var login by remember { mutableStateOf("") }
    var passwordVal by remember { mutableStateOf("") }

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = "Здравствуйте,")
            HeadingTextComponent(value = "добро пожаловать назад")
            MyTextFieldComponent(labelValue = "Логин",
                onTextChanged = { text ->
                    isLoginFieldFilled = text.isNotEmpty()
                    login = text
                })
            PasswordTextFieldComponent(labelValue = "Пароль",
                onPasswordChanged = { password ->
                    isPasswordFieldFilled = password.isNotEmpty()
                    passwordVal = password
                })
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(
                value = "Войти",
                onButtonClicked = {
                    if (isLoginFieldFilled && isPasswordFieldFilled) {
                        GlobalScope.launch(Dispatchers.IO) {
                            val result = withContext(Dispatchers.Default) {
                                httpGetUser(UserLogin(login, passwordVal))
                            }
                            withContext(Dispatchers.Main) {
                                if (result) {
                                    // Запрос выполнен успешно
                                    AppRouter.navigateTo(Screen.MainScreen)
                                } else {
                                    // Произошла ошибка
                                    Toast.makeText(context, "Произошла ошибка", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    } else{
                        Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                    }
                })
            DividerTextComponent()
            ClickableLoginTextComponent(tryingToLogIn = true, onTextSelected = {
                AppRouter.navigateTo(Screen.SignUpScreen)
            })
        }
    }
}