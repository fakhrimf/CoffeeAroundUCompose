package com.sixgroup.coffeearounducompose.ui.detail

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.DummyModel
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.Grey
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat

class DetailView {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DetailPage(model: ProductModel, context: Context, tokoModel: TokoModel) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Detail")
                    }
                )
            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(model.foto)
                        .crossfade(true)
                        .build(),
                    contentDescription = model.nama,
                    placeholder = painterResource(id = R.drawable.coffee_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxWidth()
                        .height(224.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Text(
                    text = model.nama,
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start=30.dp)
                )
                Text(
                    text = tokoModel.nama,
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Grey,
                    modifier = Modifier.padding(start=30.dp,top=10.dp)
                )
                Row(){
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.padding(start=30.dp,top=10.dp)
                    )
                    Text(
                        text = model.rating.toString(),
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start =8.dp,top=10.dp),
                        )
                    Text(
                        text = "(total)",
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Grey,
                        modifier = Modifier.padding(start = 2.dp, top = 10.dp),
                    )
                }
                    Text(
                        text = "Description",
                        fontSize = 16.sp,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 30.dp,top=40.dp)
,                    )
                    Text(
                        text = model.deskripsi_produk,
                        fontSize = 14.sp,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Normal,
                        color = Grey,
                        modifier = Modifier.padding(start=30.dp,top=12.dp),
                    )
                    Text(
                        text = "Size",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start=30.dp,top=20.dp),
                    )
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal=30.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                       Button(onClick = { /*TODO*/ },
                           shape = RoundedCornerShape(8.dp),
                           border = BorderStroke(1.dp, Grey),
                           colors = ButtonDefaults.buttonColors(
                               containerColor = Color.White,
                           ),
                           modifier = Modifier
                               .padding(top = 10.dp)
                               .height(44.dp)
                               .width(96.dp),
                       ) {
                           Text(text = "S",color= Color.Black)
                       }
                        Button(onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Grey),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                            ),
                            modifier = Modifier
                                .padding(start = 12.dp, top = 10.dp)
                                .height(44.dp)
                                .width(96.dp),
                        ) {
                            Text(text = "M",color= Color.Black)
                        }
                        Button(onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Grey),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                            ),
                            modifier = Modifier
                                .padding(start = 12.dp, top = 10.dp)
                                .height(44.dp)
                                .width(96.dp),
                        ) {
                            Text(text = "L",color= Color.Black)
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal=30.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {
                            Text(
                                text = "Price",
                                fontSize = 14.sp,
                                color = Grey,
                                fontWeight = FontWeight.Normal,
                                fontFamily = MontSerrat,
                                modifier = Modifier.padding(top=24.dp)
                            )
                            Row(){
                                Text(text = "Rp ",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = Accent,
                                    modifier = Modifier.padding(top=10.dp))
                                Text(text = model.harga.toString(),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = Accent,
                                    modifier = Modifier.padding(top=10.dp),
                                    )
                            }
                        }

                        Button(onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Accent),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Accent,
                            ),
                            modifier = Modifier
                                .padding(start = 14.dp, top = 32.dp)
                                .height(62.dp)
                                .width(218.dp),) {
                            Text(text = "Purchase Now",color= Color.White,fontSize=16.sp, fontWeight = FontWeight.Bold)
                        }
                    }


            }
        }


    }

    @Preview
    @Composable
    fun DetailPagePreview() {
        CoffeeAroundUComposeTheme {
            DetailPage(
                model = DummyModel.generateDummyProducts(1)[0],
                context = LocalContext.current,
                tokoModel = DummyModel.generateDummyTokos(1)[0]
            )
        }
    }
}