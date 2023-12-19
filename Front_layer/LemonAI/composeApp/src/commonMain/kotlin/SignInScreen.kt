import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SignInScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column (modifier = Modifier.fillMaxSize()){
            NormalTextComponent(value = "Здравствуйте,")
            HeadingTextComponent(value = "добро пожаловать назад")
            MyTextFieldComponent(labelValue = "Логин")
            PasswordTextFieldComponent(labelValue = "Пароль")
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(value = "Войти")
            DividerTextComponent()
            ClickableLoginTextComponent(onTextSelected = {

            })
        }



    }
}