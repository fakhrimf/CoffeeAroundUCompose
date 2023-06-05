package com.sixgroup.coffeearounducompose.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.utils.ApiClient
import com.sixgroup.coffeearounducompose.utils.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
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
}