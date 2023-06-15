package com.sixgroup.coffeearounducompose.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, "home")
    object Transaksi : BottomNavItem("Transaksi", Icons.Filled.List, "transaksi")
    object Akun : BottomNavItem("Akun", Icons.Filled.Person, "akun")
    object Search : BottomNavItem("Search", Icons.Filled.Search, "search")
}