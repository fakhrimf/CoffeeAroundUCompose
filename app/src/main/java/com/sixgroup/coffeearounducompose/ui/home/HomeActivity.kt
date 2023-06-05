package com.sixgroup.coffeearounducompose.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.DummyModel
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.model.UserModel
import com.sixgroup.coffeearounducompose.ui.akun.AkunView
import com.sixgroup.coffeearounducompose.ui.items.ItemCafeHome
import com.sixgroup.coffeearounducompose.ui.items.ItemCoffeeHome
import com.sixgroup.coffeearounducompose.ui.items.ItemTryHome
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.ui.transaksi.TransaksiView
import com.sixgroup.coffeearounducompose.utils.BottomNavItem
import com.sixgroup.coffeearounducompose.utils.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAroundUComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeContent(this)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(activity: ComponentActivity) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) {
        Box {
            MainNavGraph(
                navHostController = navController,
                context = LocalContext.current,
                modifier = Modifier.padding(it),
                activity = activity
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Image(
                painter = painterResource(id = R.drawable.coffeebeans),
                contentDescription = "Coffee Around U",
                modifier = Modifier
                    .padding(5.dp)
                    .width(32.dp)
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = DarkBrown
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout",
                    tint = DarkBrown
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewTopBar() {
    CoffeeAroundUComposeTheme {
        TopBar()
    }
}

@Composable
fun HomeView(modifier: Modifier = Modifier, activity: ComponentActivity) {
    val viewModel = ViewModelProvider(activity)[HomeViewModel::class.java]
    viewModel.getAllProducts()
    viewModel.getAllTokos()
    val products by viewModel.products.collectAsState()
    val tokos by viewModel.tokos.collectAsState()
    if (products.isEmpty() || tokos.isEmpty()) {
        Column(modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(
                    color = Accent
                )
            }
        }
    } else {
        Column(modifier = modifier) {
            Text(
                text = "Coffee Around U",
                fontFamily = MontSerrat,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp)
            )
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(products) {index, model ->
                    if (index == 0) {
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                    ItemCoffeeHome().ItemCoffee(
                        context = LocalContext.current,
                        model = model,
                    )
                }
            }
            Text(
                text = "Cafe Around U",
                fontFamily = MontSerrat,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp)
            )
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(tokos) {index, it ->
                    if (index == 0) {
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                    ItemCafeHome().ItemCafe(
                        context = LocalContext.current,
                        tokoModel = it
                    )
                }
            }
            Text(
                text = "Try this!",
                fontFamily = MontSerrat,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp)
            )
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                val tryArray = ArrayList<ProductModel>()
                for (i in 0 until 5)
                    tryArray.add(products.random())
                itemsIndexed(tryArray) {index, model ->
                    if (index == 0) {
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                    ItemTryHome().ItemCoffee(
                        context = LocalContext.current,
                        model = model,
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Transaksi,
        BottomNavItem.Akun,
    )
    NavigationBar(
        contentColor = DarkBrown,
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach {
            NavigationBarItem(
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                label = {
                    Text(text = it.title, fontSize = 12.sp)
                },
                selected = currentRoute == it.route,
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = DarkBrown,
                    selectedIconColor = DarkBrown,
                    indicatorColor = Accent,
                    unselectedIconColor = DarkBrown.copy(0.4f),
                    unselectedTextColor = DarkBrown.copy(0.4f)
                ),
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier,
    activity: ComponentActivity
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        composable(BottomNavItem.Home.route) {
            HomeView(activity = activity)
        }
        composable(BottomNavItem.Transaksi.route) {
            TransaksiView(context = context)
        }
        composable(BottomNavItem.Akun.route) {
            val userModel = UserModel(
                id = 1,
                address = "Rumah",
                email = "User@gmail.com",
                foto = "https://cdn.discordapp.com/attachments/775027234096414720/1079261802526945401/image.png",
                name = "MePet",
                password = "lohe",
                phone_number = "+62821756390",
                role = "user",
                token = ""
            )
            AkunView().AkunPage(model = Repository.getLoginKey(context), context = context)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    CoffeeAroundUComposeTheme {
        HomeContent(HomeActivity())
    }
}