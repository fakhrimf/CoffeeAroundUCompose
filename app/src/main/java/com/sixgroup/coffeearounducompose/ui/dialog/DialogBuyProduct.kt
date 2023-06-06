package com.sixgroup.coffeearounducompose.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat

class DialogBuyProduct {
    @Composable
    fun GetDialogBuyProduct(
        openDialog: MutableState<Boolean>,
        buyProduct: MutableState<Boolean>
    ) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            DialogBuyProductView(openDialog = openDialog, buyProduct = buyProduct)
        }
    }

    @Composable
    fun DialogBuyProductView(
        modifier: Modifier = Modifier,
        openDialog: MutableState<Boolean>,
        buyProduct: MutableState<Boolean>
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = modifier
                .width(500.dp)
                .padding(25.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Icon(
                    imageVector = Icons.Filled.QuestionMark,
                    contentDescription = "picture",
                    tint = Accent,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(32.dp)
                        .width(32.dp)
                )
            }
            Text(
                text = "Beli produk ini?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 10.dp),
                textAlign = TextAlign.Center,
                fontFamily = MontSerrat,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        openDialog.value = false
                    },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(130.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent.copy(0.4f)
                    )
                ) {
                    Text(text = "Tidak", fontFamily = MontSerrat, color = Color.White)
                }
                Button(
                    onClick = {
                        buyProduct.value = true
                        openDialog.value = false
                    },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(130.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent
                    )
                ) {
                    Text(text = "Beli", fontFamily = MontSerrat, color = Color.White)
                }
            }
        }
    }

    @Preview
    @Composable
    fun DialogBuyProductPreview() {
        CoffeeAroundUComposeTheme {
//            DialogBuyProductView()
        }
    }
}