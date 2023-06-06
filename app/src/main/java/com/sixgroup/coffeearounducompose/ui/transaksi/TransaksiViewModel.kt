package com.sixgroup.coffeearounducompose.ui.transaksi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixgroup.coffeearounducompose.model.TransactionResponse
import com.sixgroup.coffeearounducompose.utils.ApiClient
import com.sixgroup.coffeearounducompose.utils.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransaksiViewModel: ViewModel() {
    val placeholder: TransactionResponse? = null
    val _response = MutableStateFlow(placeholder)
    val response = _response.asStateFlow()

    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun getTransactions(idUser: Int) {
        viewModelScope.launch {
            val call = apiInterface.getTransaksiById(idUser)
            _response.update {
                call
            }
        }
    }
}