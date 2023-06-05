package com.sixgroup.coffeearounducompose.ui.items

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.ui.detail.DetailViewActivity
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.Grey
import com.sixgroup.coffeearounducompose.ui.theme.LightDark
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.Constants
import java.text.DecimalFormat

class ItemTryHome {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ItemCoffee(
        modifier: Modifier = Modifier,
        context: Context,
        model: ProductModel
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Accent.copy(0.2f)),
            modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp).fillMaxWidth(),
            onClick = {
                val intent = Intent(context, DetailViewActivity::class.java)
                intent.putExtra(Constants.PRODUCT_DETAIL, model)
                context.startActivity(intent)
            }
        ) {
            Row(modifier = modifier
                .fillMaxWidth()
                ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data("${Constants.IMAGE_URL}${model.foto}")
                        .crossfade(true)
                        .build(),
                    contentDescription = model.nama,
                    placeholder = painterResource(id = R.drawable.coffee_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        .height(170.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column(modifier = Modifier.padding(10.dp)) {
                    val formatter = DecimalFormat("#,###")
                    Text(
                        text = model.nama,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DarkBrown,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(150.dp)
                    )
                    Text(
                        text = model.nama_toko,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        color = Grey,
                        fontSize = 12.sp,
                        maxLines = 2,
                        lineHeight = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(130.dp)
                    )
                    Text(
                        text = "Rp ${formatter.format(model.harga)}",
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = LightDark,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width(120.dp)
                            .padding(bottom = 10.dp, top = 5.dp)
                    )
                    Text(
                        text = model.deskripsi_produk,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        color = Grey,
                        fontSize = 12.sp,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(150.dp),
                        lineHeight = 14.sp
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun ItemCoffeePreview() {
        val model = ProductModel(
            1,
            "Cappuccino",
            "Nice",
            21000,
            5.0.toFloat(),
            "https://img.freepik.com/free-photo/top-view-hot-espresso-with-brown-coffee-seeds-brown-wooden-desk-coffee-cup-drink_140725-28168.jpg?w=740&t=st=1685772072~exp=1685772672~hmac=5377644915f2e0afe784484004834a50a7e3be388e1fbdf1a77a3ef5fa585671",
            1,
            "",
            1,
            "broshop"
        )
        CoffeeAroundUComposeTheme {
            ItemCoffee(context = LocalContext.current, model = model)
        }
    }
}