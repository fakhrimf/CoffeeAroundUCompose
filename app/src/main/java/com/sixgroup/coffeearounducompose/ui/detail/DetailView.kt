package com.sixgroup.coffeearounducompose.ui.detail

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.DummyModel
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.ui.theme.Star
import com.sixgroup.coffeearounducompose.utils.Constants
import java.text.DecimalFormat

class DetailView {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DetailPage(model: ProductModel, context: Context, activity: ComponentActivity) {
        val vm = ViewModelProvider(activity)[DetailViewModel::class.java]
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Detail",
                            color = DarkBrown,
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = { activity.onBackPressed() }) {
                            Icon(
                                imageVector = Icons.Filled.ChevronLeft,
                                contentDescription = "back",
                                tint = DarkBrown.copy(0.5f)
                            )
                        }
                    }
                )
            }, containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
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
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .height(450.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Text(
                    text = model.nama,
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 30.dp)
                )
                Row {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Star,
                        modifier = Modifier.padding(start = 30.dp, top = 10.dp)
                    )
                    Text(
                        text = model.rating.toString(),
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 10.dp),
                    )
//                    Text(
//                        text = "(total)",
//                        fontFamily = MontSerrat,
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 12.sp,
//                        color = Grey,
//                        modifier = Modifier.padding(start = 2.dp, top = 10.dp),
//                    )
                }
                vm.getToko(model.toko_id)
                val toko by vm.toko.collectAsState()
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(containerColor = Accent.copy(0.2f)),
                    onClick = {
                        val intent = Intent(context, DetailCafeActivity::class.java)
                        intent.putExtra(Constants.TOKO_DETAIL, toko)
                        context.startActivity(intent)
                    }
                ) {
                    if (toko == null) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = Accent,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    } else {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = ImageRequest.Builder(context = context)
                                    .data("${Constants.IMAGE_URL}${toko?.icon_url}")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = model.nama,
                                placeholder = painterResource(id = R.drawable.coffee_placeholder),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(32.dp)
                                    .height(32.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Text(
                                text = model.nama_toko,
                                fontSize = 16.sp,
                                fontFamily = MontSerrat,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(vertical = 16.dp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ChevronRight,
                                    contentDescription = "Go to toko",
                                    modifier = Modifier.padding(14.dp),
                                    tint = DarkBrown.copy(0.5f)
                                )
                            }
                        }
                    }
                }

                Text(
                    text = "Description",
                    fontSize = 18.sp,
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 30.dp, top = 30.dp),
                )
                Text(
                    text = model.deskripsi_produk,
                    fontSize = 16.sp,
                    lineHeight = 14.sp,
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(0.7f),
                    modifier = Modifier.padding(start = 30.dp, top = 12.dp),
                )
//                    Text(
//                        text = "Size",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black,
//                        modifier = Modifier.padding(start=30.dp,top=20.dp),
//                    )
//                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal=30.dp), horizontalArrangement = Arrangement.SpaceBetween) {
//                       Button(onClick = { /*TODO*/ },
//                           shape = RoundedCornerShape(8.dp),
//                           border = BorderStroke(1.dp, Grey),
//                           colors = ButtonDefaults.buttonColors(
//                               containerColor = Color.White,
//                           ),
//                           modifier = Modifier
//                               .padding(top = 10.dp)
//                               .height(44.dp)
//                               .width(96.dp),
//                       ) {
//                           Text(text = "S",color= Color.Black)
//                       }
//                        Button(onClick = { /*TODO*/ },
//                            shape = RoundedCornerShape(8.dp),
//                            border = BorderStroke(1.dp, Grey),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color.White,
//                            ),
//                            modifier = Modifier
//                                .padding(start = 12.dp, top = 10.dp)
//                                .height(44.dp)
//                                .width(96.dp),
//                        ) {
//                            Text(text = "M",color= Color.Black)
//                        }
//                        Button(onClick = { /*TODO*/ },
//                            shape = RoundedCornerShape(8.dp),
//                            border = BorderStroke(1.dp, Grey),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color.White,
//                            ),
//                            modifier = Modifier
//                                .padding(start = 12.dp, top = 10.dp)
//                                .height(44.dp)
//                                .width(96.dp),
//                        ) {
//                            Text(text = "L",color= Color.Black)
//                        }
//                    }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Price",
                            fontSize = 16.sp,
                            color = DarkBrown,
                            fontWeight = FontWeight.Normal,
                            fontFamily = MontSerrat,
                            modifier = Modifier.padding(top = 24.dp)
                        )

                        val formatter = DecimalFormat("#,###")
                        Text(
                            text = "Rp ${formatter.format(model.harga)}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = Accent,
                            modifier = Modifier.padding(top = 10.dp),
                            fontFamily = MontSerrat
                        )
                    }

                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Accent),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent,
                        ),
                        modifier = Modifier
                            .padding(start = 14.dp, top = 32.dp)
                            .height(62.dp)
                            .width(218.dp),
                    ) {
                        Text(
                            text = "Purchase Now",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontSerrat
                        )
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
                activity = DetailViewActivity()
            )
        }
    }
}