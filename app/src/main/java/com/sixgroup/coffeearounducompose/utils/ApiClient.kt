package com.sixgroup.coffeearounducompose.utils

import com.sixgroup.coffeearounducompose.model.LoginResponse
import com.sixgroup.coffeearounducompose.model.ProductResponse
import com.sixgroup.coffeearounducompose.model.TokoResponse
import com.sixgroup.coffeearounducompose.model.TokosResponse
import com.sixgroup.coffeearounducompose.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        fun getClient(): Retrofit {
            val client = OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}

interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email")
        email: String,
        @Field("password")
        password: String,
    ): Call<LoginResponse>

    @GET("my_product/{id}/by_toko")
    suspend fun getProductByToko(
        @Path("id")
        id: Int
    ) : ProductResponse

    @GET("my_product")
    suspend fun getAllProducts(): ProductResponse

    @GET("my_toko")
    suspend fun getAllTokos(): TokosResponse

    @GET("my_toko/{id}/show")
    suspend fun getToko(
        @Path("id")
        id: Int
    ): TokoResponse
}
