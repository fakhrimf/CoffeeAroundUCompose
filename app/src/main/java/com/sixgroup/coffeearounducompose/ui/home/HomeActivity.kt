package com.sixgroup.coffeearounducompose.ui.home

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sixgroup.coffeearoundu.model.UserModel
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.model.DummyModel
import com.sixgroup.coffeearounducompose.ui.akun.AkunView
import com.sixgroup.coffeearounducompose.ui.items.ItemCafeHome
import com.sixgroup.coffeearounducompose.ui.items.ItemCoffeeHome
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.ui.transaksi.TransaksiView
import com.sixgroup.coffeearounducompose.utils.BottomNavItem

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
                    HomeContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent() {
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
                modifier = Modifier.padding(it)
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
fun HomeView(modifier: Modifier = Modifier) {
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
            val prods = DummyModel.generateDummyProducts(10)
            val tokos = DummyModel.generateDummyTokos(10)
            items(prods.toList(), key = { it.id }) {
                if (it.id == 0) {
                    Spacer(modifier = Modifier.padding(5.dp))
                }
                ItemCoffeeHome().ItemCoffee(
                    context = LocalContext.current,
                    model = it,
                    tokoModel = tokos[it.id_toko]
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
            val tokos = DummyModel.generateDummyTokos(10)
            items(tokos.toList(), key = { it.id }) {
                if (it.id == 0) {
                    Spacer(modifier = Modifier.padding(5.dp))
                }
                ItemCafeHome().ItemCafe(
                    context = LocalContext.current,
                    tokoModel = it
                )
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
    modifier: Modifier = Modifier
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
            HomeView()
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
                role = "user"
            )
            AkunView().AkunPage(model = userModel, context = context)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    CoffeeAroundUComposeTheme {
        HomeContent()
    }
}