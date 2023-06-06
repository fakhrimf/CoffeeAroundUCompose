package com.sixgroup.coffeearounducompose.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixgroup.coffeearounducompose.model.RegisterResponse
import com.sixgroup.coffeearounducompose.model.RegisterUserModel
import com.sixgroup.coffeearounducompose.utils.ApiClient
import com.sixgroup.coffeearounducompose.utils.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val placeholder: RegisterResponse? = null
    val _response = MutableStateFlow(placeholder)
    val response = _response.asStateFlow()

    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun register(registerUserModel: RegisterUserModel) {
        viewModelScope.launch {
            val register = apiInterface.register(registerUserModel)
            _response.update {
                register
            }
        }
    }
}