package com.sixgroup.coffeearounducompose.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.DummyModel
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.ui.items.ItemTryHome
import com.sixgroup.coffeearounducompose.ui.maps.MapsActivity
import com.sixgroup.coffeearounducompose.ui.maps.MapsActivityViews
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.Constants
import com.sixgroup.coffeearounducompose.utils.Constants.TOKO_DETAIL

class DetailCafeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toko = intent.getParcelableExtra<TokoModel>(TOKO_DETAIL)
        setContent {
            CoffeeAroundUComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CafeDetailView(model = toko!!, context = LocalContext.current, activity = this)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CafeDetailView(model: TokoModel, context: Context, activity: ComponentActivity) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detail Toko",
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
                })
        },
    ) {
        val vm = ViewModelProvider(activity)[DetailViewModel::class.java]
        val products by vm.products.collectAsState()
        vm.getProductsByToko(model.id)
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data("${Constants.IMAGE_URL}${model.icon_url}")
                        .crossfade(true)
                        .build(),
                    contentDescription = model.nama,
                    placeholder = painterResource(id = R.drawable.coffee_placeholder),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                        .height(250.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            Text(
                text = model.nama,
                fontFamily = MontSerrat,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 30.dp, top = 10.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, top = 5.dp, end = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Accent.copy(0.2f)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                ),
                onClick = {
                    val intent = Intent(context, MapsActivityViews::class.java)
                    intent.putExtra(TOKO_DETAIL, model)
                    context.startActivity(intent)
                }
            ) {
                Box {
                    Text(
                        text = model.address,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color.Black,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            top = 10.dp,
                            start = 10.dp,
                            bottom = 10.dp,
                            end = 30.dp
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = "Go to maps",
                            tint = DarkBrown.copy(0.5f),
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
            }
            Text(
                text = "Menu",
                color = Color.Black,
                fontFamily = MontSerrat,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
            )
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                LazyColumn(content = {
                    itemsIndexed(products) { index, model ->
                        if (index == 0) {
                            Spacer(modifier = Modifier.padding(5.dp))
                        }
                        ItemTryHome().ItemCoffee(
                            context = LocalContext.current,
                            model = model,
                        )
                        if (index == products.size - 1) {
                            Spacer(modifier = Modifier.padding(5.dp))
                        }
                    }
                }, modifier = Modifier.padding(horizontal = 30.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CafeDetailPreview() {
    CoffeeAroundUComposeTheme {
        CafeDetailView(
            context = LocalContext.current,
            model = DummyModel.generateDummyTokos(1)[0],
            activity = DetailCafeActivity()
        )
    }
}