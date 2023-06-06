package com.sixgroup.coffeearounducompose.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.ui.home.HomeActivity
import com.sixgroup.coffeearounducompose.ui.register.RegisterActivity
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.Background
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.CustomView.ButtonText
import com.sixgroup.coffeearounducompose.utils.CustomView.InfoText
import com.sixgroup.coffeearounducompose.utils.CustomView.TextClick
import com.sixgroup.coffeearounducompose.utils.CustomView.Title
import es.dmoral.toasty.Toasty

class LoginView {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginMain(context: Context, activity: ComponentActivity) {
        Column(
            modifier = Modifier
                .background(Background)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            val viewModel = ViewModelProvider(activity)[LoginViewModel::class.java]
            var loginInput by rememberSaveable {
                mutableStateOf("")
            }
            var passwordInput by rememberSaveable {
                mutableStateOf("")
            }
            var isPasswordVisible by rememberSaveable {
                mutableStateOf(false)
            }
            var isChecked by rememberSaveable {
                mutableStateOf(false)
            }
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coffeebeans),
                    contentDescription = "Coffee Around U",
                    modifier = Modifier
                        .padding(top = 72.dp)
                        .width(104.dp)
                        .height(104.dp)
                )
            }
            Title(
                text = "Coffee Around U",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(24.dp)
            )

            // E-Mail TextField
            TextField(
                value = loginInput,
                onValueChange = { loginInput = it },
                label = { Text(text = "E-Mail") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "email_icon") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                textStyle = TextStyle(
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 80.dp)
                    .height(60.dp)
                    .clip(CircleShape),
                colors = textFieldColors
            )

            // Password Field
            TextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = { Text(text = "Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "email_icon") },
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
//            Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
//                Checkbox(checked = isChecked, onCheckedChange = { isChecked = !isChecked })
//                TextClick(
//                    text = "Remember Me",
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
            var isLoggingIn by rememberSaveable {
                mutableStateOf(false)
            }
            Button(
                onClick = {
                    isLoggingIn = true
                    val login = viewModel.login(loginInput, passwordInput, context)
                    login.observe(activity) {
                        Log.d("Hasil Login", "LoginMain: $it")
                        isLoggingIn = if (it.message == null) {
                            context.startActivity(Intent(context, HomeActivity::class.java))
                            activity.finish()
                            false
                        } else {
                            Toasty.error(context, "Email atau Password Salah").show()
                            false
                        }
                    }
//                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                colors = ButtonDefaults.buttonColors(containerColor = Accent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
                    .height(60.dp),
                enabled = !isLoggingIn,
                content = {
                    if (isLoggingIn) {
                        CircularProgressIndicator(color = Accent)
                    } else {
                        ButtonText(text = "LOGIN")
                    }
                },
            )

            Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
                InfoText(text = "No Account?")
                TextClick(text = "Register Now",
                    Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            context.startActivity(Intent(context, RegisterActivity::class.java))
                        })
            }
        }
    }

    @Preview
    @Composable
    fun MainPreview() {
        CoffeeAroundUComposeTheme {
//            LoginMain(LocalContext.current, this, )
        }
    }
}