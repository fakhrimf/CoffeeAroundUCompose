package com.sixgroup.coffeearounducompose.ui.items

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.ui.detail.DetailViewActivity
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.Grey
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.Constants
import com.sixgroup.coffeearounducompose.utils.Constants.IMAGE_URL
import java.text.DecimalFormat

class ItemTransaction {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ItemTransactionView(
        model: ProductModel,
        context: Context,
        isOngoing: Boolean = true
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Accent.copy(0.2f)),
            onClick = {
                val intent = Intent(context, DetailViewActivity::class.java)
                intent.putExtra(Constants.PRODUCT_DETAIL, model)
                context.startActivity(intent)
            }
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data("$IMAGE_URL${model.foto}")
                        .crossfade(true)
                        .build(),
                    contentDescription = model.nama,
                    placeholder = painterResource(id = R.drawable.coffee_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(74.dp)
                        .height(74.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column {
                    Text(
                        text = model.nama,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = DarkBrown,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = model.nama_toko,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Grey,
                    )
                }
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        val formatter = DecimalFormat("#,###")
                        Card(
                            modifier = Modifier.padding(top = 10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isOngoing) Color.Green.copy(0.3f) else DarkBrown.copy(
                                    0.3f
                                )
                            )
                        ) {
                            Text(
                                text = "Ready",
                                fontFamily = MontSerrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        Text(
                            text = "Rp ${formatter.format(model.harga)}",
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = DarkBrown,
                            modifier = Modifier.padding(top = 24.dp)
                        )
                    }
                }
            }
        }
    }

    @Preview
    /*(showBackground = true)*/
    @Composable
    fun ItemTransactionPreview() {
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
            "gayming"
        )
        val tokoModel = TokoModel(
            1,
            "Tel-U Coffee",
            "Jl. Telekomunikasi No. 1",
            5.0.toString(),
            (-100.0).toString(),
            "https://img.freepik.com/free-photo/top-view-hot-espresso-with-brown-coffee-seeds-brown-wooden-desk-coffee-cup-drink_140725-28168.jpg?w=740&t=st=1685772072~exp=1685772672~hmac=5377644915f2e0afe784484004834a50a7e3be388e1fbdf1a77a3ef5fa585671",
            1
        )
//        ItemTransactionView(model = model, tokoModel = tokoModel, context = LocalContext.current)
    }
}