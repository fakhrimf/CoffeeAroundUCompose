package com.sixgroup.coffeearounducompose.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sixgroup.coffeearounducompose.model.LoginResponse
import com.sixgroup.coffeearounducompose.model.UserModel
import com.sixgroup.coffeearounducompose.utils.ApiClient
import com.sixgroup.coffeearounducompose.utils.ApiInterface
import com.sixgroup.coffeearounducompose.utils.Constants.LOGIN_KEY
import com.sixgroup.coffeearounducompose.utils.Constants.SHARED_KEY
import com.sixgroup.coffeearounducompose.utils.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun login(email: String, password: String, context: Context): MutableLiveData<LoginResponse> {
        val call: Call<LoginResponse> = apiInterface.login(email, password)
        val user = MutableLiveData<LoginResponse>()
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                user.value = response.body()
                Log.d("Response Body", "onResponse: ${response.body()}")
                if (response.body()?.data != null)
                    Repository.storeLoginKey(context, response.body()!!.data)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                call.cancel()
            }
        })
        return user
    }
}