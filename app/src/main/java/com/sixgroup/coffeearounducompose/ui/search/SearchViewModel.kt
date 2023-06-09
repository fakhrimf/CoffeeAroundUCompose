package com.sixgroup.coffeearounducompose.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.utils.ApiClient
import com.sixgroup.coffeearounducompose.utils.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val _products = MutableStateFlow(ArrayList<ProductModel>())
    val products = _products.asStateFlow()

    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun getAllProducts() {
        viewModelScope.launch {
            val call = apiInterface.getAllProducts()
            Log.d("HASIL RESPONSE", "getAllProducts: $call")
            _products.update {
                call.data
            }
        }
    }
}