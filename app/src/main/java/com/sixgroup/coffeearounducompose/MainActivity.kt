package com.sixgroup.coffeearounducompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.sixgroup.coffeearounducompose.ui.home.HomeActivity
import com.sixgroup.coffeearounducompose.ui.login.LoginView
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.utils.Repository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAroundUComposeTheme {
                // A surface container using the 'background' color from the theme
                if (Repository.checkLogin(context = LocalContext.current)) {
                    startActivity(Intent(LocalContext.current, HomeActivity::class.java))
                    this.finish()
                }
                LoginView().LoginMain(context = LocalContext.current, activity = this)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeAroundUComposeTheme {
        LoginView().LoginMain(context = LocalContext.current, activity = MainActivity())
    }
}