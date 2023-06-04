package com.sixgroup.coffeearounducompose.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data:UserModel
) : Parcelable

@Parcelize
data class ProductResponse(
    @SerializedName("data")
    val data: ArrayList<ProductModel>
) : Parcelable
