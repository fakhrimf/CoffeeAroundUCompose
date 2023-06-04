package com.sixgroup.coffeearounducompose.ui.transaksi

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixgroup.coffeearounducompose.model.DummyModel
import com.sixgroup.coffeearounducompose.ui.items.ItemTransaction
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.utils.TabItem
import kotlinx.coroutines.launch

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
fun TransaksiView(context: Context, modifier: Modifier = Modifier) {
    val tabs = listOf(
        TabItem(
            title = "Ongoing",
            icon = Icons.Filled.Coffee,
            screen = {
                TabScreenOngoing(context = context)
            }
        ),
        TabItem(
            title = "Selesai",
            icon = Icons.Filled.Check,
            screen = {
                TabScreenDone(context = context)
            }
        )
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        tabs.size // provide pageCount
    }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = {
            },
            containerColor = Color.White,
            contentColor = DarkBrown
        ) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    selectedContentColor = DarkBrown,
                    unselectedContentColor = DarkBrown.copy(0.4f),
                    selected = index == pagerState.currentPage,
                    text = { Text(text = tabItem.title) },
                    icon = { Icon(imageVector = tabItem.icon, "") },
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                )
            }
        }
        HorizontalPager(state = pagerState) {
            when(it) {
                0 -> {
                    TabScreenOngoing(context = context)
                }
                1 -> {
                    TabScreenDone(context = context)
                }
            }
        }
    }
}

@Composable
fun TabScreenOngoing(context: Context) {
    LazyColumn {
        val prods = DummyModel.generateDummyProducts(10)
        val tokos = DummyModel.generateDummyTokos(10)
        items(prods.toList(), key = { it.id }) {
            Spacer(modifier = Modifier.padding(5.dp))
            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                ItemTransaction().ItemTransactionView(
                    model = it,
                    tokoModel = tokos[it.id_toko],
                    context = context,
                    isOngoing = true
                )
            }
        }
    }
}


@Composable
fun TabScreenDone(context: Context) {
    LazyColumn {
        val prods = DummyModel.generateDummyProducts(10)
        val tokos = DummyModel.generateDummyTokos(10)
        items(prods.toList(), key = { it.id }) {
            Spacer(modifier = Modifier.padding(5.dp))
            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                ItemTransaction().ItemTransactionView(
                    model = it,
                    tokoModel = tokos[it.id_toko],
                    context = context,
                    isOngoing = false
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransaksiPreview() {
    CoffeeAroundUComposeTheme {
        TransaksiView(context = LocalContext.current)
    }
}