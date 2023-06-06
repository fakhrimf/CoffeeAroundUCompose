package com.sixgroup.coffeearounducompose.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixgroup.coffeearounducompose.model.ProductModel
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.utils.ApiClient
import com.sixgroup.coffeearounducompose.utils.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    val _products = MutableStateFlow(ArrayList<ProductModel>())
    val products = _products.asStateFlow()
    val _buy = MutableStateFlow(false)
    val buy = _buy.asStateFlow()
    var placeholder: TokoModel? = null
    val _toko = MutableStateFlow(placeholder)
    val toko = _toko.asStateFlow()


    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun getToko(id: Int) {
        viewModelScope.launch {
            val call = apiInterface.getToko(id)
            Log.d("HASIL RESPONSE", "getAllTokos: $call")
            _toko.update {
                call.data
            }
        }
    }

    fun getProductsByToko(id: Int) {
        viewModelScope.launch {
            val call = apiInterface.getProductByToko(id)
            Log.d("HASIL RESPONSE", "getAllTokos: $call")
            _products.update {
                call.data
            }
        }
    }

    fun buyProduct(userId: Int, productId: Int) {
        viewModelScope.launch {
            val transaksi = apiInterface.transaksi(productId, userId)
            _buy.update {
                transaksi.success
            }
        }
    }
}