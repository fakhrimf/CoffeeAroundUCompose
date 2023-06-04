package com.sixgroup.coffeearounducompose.ui.register

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.Background
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.CustomView

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAroundUComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterView(context = LocalContext.current)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(context: Context) {
    Scaffold(
        containerColor = Background,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Background),
                title = {
                    Text(
                        text = "Register",
                        color = DarkBrown,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    )
                }
            )
        }
    ) {
        RegisterFields(context = context, modifier = Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterFields(context: Context, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val textFieldColors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            textColor = DarkBrown,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = Accent,
            focusedTrailingIconColor = Accent,
            focusedLabelColor = Accent,
            unfocusedTrailingIconColor = DarkBrown,
            unfocusedLeadingIconColor = DarkBrown,
            unfocusedLabelColor = DarkBrown
        )
        var loginInput by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var nameInput by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var phoneInput by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var passwordInput by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var isPasswordVisible by remember {
            mutableStateOf(false)
        }
        var addressInput by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(DarkBrown)
                ) {
                    Image(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Picture",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .width(120.dp)
                            .height(120.dp)
                            .clip(CircleShape)
                    )
                }
                Box(
                    modifier = Modifier
                        .offset(x = 82.dp, y = 82.dp)
                        .clip(CircleShape)
                        .background(Accent)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit Profile Picture",
                        modifier = Modifier.padding(7.dp),
                        tint = Color.White
                    )
                }
            }
        }
        // Email Input
        RegisterField(
            value = loginInput,
            onValueChange = { loginInput = it },
            label = "E-Mail",
            type = KeyboardType.Email,
            modifier = Modifier.padding(top = 10.dp)
        )
        // Name Input=
        RegisterField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = "Name",
            type = KeyboardType.Text
        )
        // Phone Number
        RegisterField(
            value = phoneInput,
            onValueChange = { phoneInput = it },
            label = "Phone",
            type = KeyboardType.Phone
        )
        // Password Field
        TextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle(
                fontFamily = MontSerrat,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            ),
            trailingIcon = {
                val image =
                    if (isPasswordVisible) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 12.dp)
                .height(60.dp)
                .clip(CircleShape),
            colors = textFieldColors
        )
        // Address Field
        RegisterField(
            value = addressInput,
            onValueChange = { addressInput = it },
            label = "Full Address",
            type = KeyboardType.Text
        )

        Button(
            onClick = {
//                    context.startActivity(Intent(context, MainActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(containerColor = Accent),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 54.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
                .height(60.dp),
            content = {
                CustomView.ButtonText(text = "REGISTER")
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    type: KeyboardType
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.White,
        textColor = DarkBrown,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = Accent,
        focusedTrailingIconColor = Accent,
        focusedLabelColor = Accent,
        unfocusedTrailingIconColor = DarkBrown,
        unfocusedLeadingIconColor = DarkBrown,
        unfocusedLabelColor = DarkBrown
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = type),
        textStyle = TextStyle(
            fontFamily = MontSerrat,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .height(60.dp)
            .clip(CircleShape),
        colors = textFieldColors
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CoffeeAroundUComposeTheme {
        RegisterView(context = LocalContext.current)
    }
}