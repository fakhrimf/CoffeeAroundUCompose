package com.sixgroup.coffeearounducompose.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.utils.Constants.PRODUCT_DETAIL

class DetailViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = intent.getParcelableExtra<ProductModel>(PRODUCT_DETAIL)
        setContent {
            CoffeeAroundUComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailView().DetailPage(
                        model = model!!,
                        context = LocalContext.current,
                        activity = this
                    )
                }
            }
        }
    }
}