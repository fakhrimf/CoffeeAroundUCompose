package com.sixgroup.coffeearounducompose.ui.search

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.ui.items.ItemTryHome
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.CustomView

class SearchProductView {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchProductPage(modifier: Modifier = Modifier, activity: ComponentActivity) {
        var searchInput by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val textFieldColors = TextFieldDefaults.textFieldColors(
                containerColor = Accent.copy(0.4f),
                textColor = DarkBrown,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = Accent,
                focusedTrailingIconColor = Accent,
                focusedLabelColor = Accent,
                unfocusedTrailingIconColor = DarkBrown,
                unfocusedLeadingIconColor = DarkBrown,
                unfocusedLabelColor = DarkBrown
            )
            TextField(
                value = searchInput,
                onValueChange = { searchInput = it },
                label = { Text(text = "Cari Produk") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "search_icon") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(60.dp)
                    .clip(CircleShape),
                colors = textFieldColors,
            )
            val vm = ViewModelProvider(activity)[SearchViewModel::class.java]
            val products by vm.products.collectAsState()
            vm.getAllProducts()
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                LazyColumn(content = {
                    if (searchInput.text.isEmpty()) {
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
                    } else {
                        val filteredProducts = ArrayList<ProductModel>()
                        for (i in products) {
                            if (i.nama.contains(searchInput.text, ignoreCase = true)) {
                                filteredProducts.add(i)
                            }
                        }
                        itemsIndexed(filteredProducts) { index, model ->
                            if (index == 0) {
                                Spacer(modifier = Modifier.padding(5.dp))
                            }
                            ItemTryHome().ItemCoffee(
                                context = LocalContext.current,
                                model = model,
                            )
                            if (index == filteredProducts.size - 1) {
                                Spacer(modifier = Modifier.padding(5.dp))
                            }
                        }
                    }
                }, modifier = Modifier.padding(horizontal = 30.dp))
            }
        }
    }

    @Composable
    @Preview
    fun SearchProductPreview() {
        CoffeeAroundUComposeTheme {
//            SearchProductPage()
        }
    }
}