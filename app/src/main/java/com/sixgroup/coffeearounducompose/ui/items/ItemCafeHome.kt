package com.sixgroup.coffeearounducompose.ui.items

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.Grey
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.Constants.IMAGE_URL

class ItemCafeHome {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ItemCafe(
        modifier: Modifier = Modifier,
        context: Context,
        tokoModel: TokoModel
    ) {
        Column(modifier = modifier.background(Color.White)) {
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data("$IMAGE_URL${tokoModel.icon_url}")
                    .crossfade(true)
                    .build(),
                contentDescription = tokoModel.nama,
                placeholder = painterResource(id = R.drawable.coffee_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                    .width(130.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column {
                Text(
                    text = tokoModel.nama,
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = DarkBrown,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(horizontal = 10.dp)
                )
                Text(
                    text = tokoModel.address,
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Medium,
                    color = Grey,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(horizontal = 10.dp)
                )
            }
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(130.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Accent
                )
            ) {
                Text(
                    text = "Explore",
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(5.dp)
                )
            }
        }
    }

    @Preview
    @Composable
    fun ItemCafePreview() {
        val tokoModel = TokoModel(
            1,
            "Tel-U Coffee",
            "Jl. Telekomunikasi No. 1",
            5.0.toFloat(),
            (-100.0).toFloat(),
            "https://img.freepik.com/free-photo/top-view-hot-espresso-with-brown-coffee-seeds-brown-wooden-desk-coffee-cup-drink_140725-28168.jpg?w=740&t=st=1685772072~exp=1685772672~hmac=5377644915f2e0afe784484004834a50a7e3be388e1fbdf1a77a3ef5fa585671",
            1
        )
        CoffeeAroundUComposeTheme {
            ItemCafe(context = LocalContext.current, tokoModel = tokoModel)
        }
    }
}