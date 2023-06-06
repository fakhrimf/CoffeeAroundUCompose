package com.sixgroup.coffeearounducompose.ui.transaksi

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.ui.items.ItemTransaction
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.Repository

class TransaksiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAroundUComposeTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransaksiView(context: Context, modifier: Modifier = Modifier, activity: ComponentActivity) {
//    val tabs = listOf(
//        TabItem(
//            title = "Ongoing",
//            icon = Icons.Filled.Coffee,
//            screen = {
//                TabScreenOngoing(context = context)
//            }
//        ),
//        TabItem(
//            title = "Selesai",
//            icon = Icons.Filled.Check,
//            screen = {
//                TabScreenDone(context = context)
//            }
//        )
//    )
//    val pagerState = rememberPagerState(
//        initialPage = 0,
//        initialPageOffsetFraction = 0f
//    ) {
//        tabs.size // provide pageCount
//    }
//    val coroutineScope = rememberCoroutineScope()
    Column(modifier = modifier.fillMaxSize()) {
        TabScreen(context = context, activity)
    }
}

@Composable
fun TabScreen(context: Context, activity: ComponentActivity) {
    val vm = ViewModelProvider(activity)[TransaksiViewModel::class.java]
    vm.getTransactions(Repository.getLoginKey(context).id)
    val response = vm.response.collectAsState()
    Text(
        text = "History Transaksi",
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
    )
    if (response.value == null) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator(color = Accent)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .fillMaxSize()
        ) {
            val prods = response.value?.data
            prods?.let {
                itemsIndexed(it.reversed().toList()) {index, tmodel ->
                    Spacer(modifier = Modifier.padding(5.dp))
                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        val model = ProductModel(
                            nama = tmodel.productName,
                            deskripsi_produk = tmodel.productDesc,
                            nama_toko = tmodel.nama_toko,
                            available = 1,
                            foto = tmodel.foto,
                            harga = tmodel.harga,
                            id = tmodel.id_produk,
                            rating = tmodel.rating,
                            reason = null,
                            toko_id = tmodel.id_toko
                        )
                        ItemTransaction().ItemTransactionView(
                            model = model,
                            context = context,
                            isOngoing = true
                        )
                    }
                    if (index == prods.size -1) {
                        Spacer(modifier = Modifier.padding(15.dp))
                    }
                }
            }
        }
    }
}


//@Composable
//fun TabScreenDone(context: Context) {
//    LazyColumn {
//        val prods = DummyModel.generateDummyProducts(10)
//        val tokos = DummyModel.generateDummyTokos(10)
//        items(prods.toList(), key = { it.id }) {
//            Spacer(modifier = Modifier.padding(5.dp))
//            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
//                ItemTransaction().ItemTransactionView(
//                    model = it,
//                    tokoModel = tokos[it.toko_id],
//                    context = context,
//                    isOngoing = false
//                )
//            }
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun TransaksiPreview() {
    CoffeeAroundUComposeTheme {
//        TransaksiView(context = LocalContext.current)
    }
}