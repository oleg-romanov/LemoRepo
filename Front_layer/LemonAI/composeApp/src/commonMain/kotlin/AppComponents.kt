import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Resource

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth().heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color(0xFF1D1617),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth().heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Color(0xFF1D1617),
        textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextFieldComponent(labelValue: String) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().clip(componentsShape.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF92A3FD),
            focusedLabelColor = Color(0xFF92A3FD),
            cursorColor = Color(0xFF92A3FD),
            backgroundColor = Color(0xFFF7F8F8)
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
    )
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String
) {

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().clip(componentsShape.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF92A3FD),
            focusedLabelColor = Color(0xFF92A3FD),
            cursorColor = Color(0xFF92A3FD),
            backgroundColor = Color(0xFFF7F8F8)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password.value,
        onValueChange = {
            password.value = it
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var discription = if (passwordVisible.value) {
                "Скрыть пароль"
            } else {
                "Показать пароль"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value} ){
                Icon(imageVector = iconImage, contentDescription = discription)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}