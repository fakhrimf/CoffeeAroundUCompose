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

@Parcelize
data class TokosResponse(
    @SerializedName("data")
    val data: ArrayList<TokoModel>
) : Parcelable

@Parcelize
data class TokoResponse(
    @SerializedName("data")
    val data: TokoModel
) : Parcelable

@Parcelize
data class RegisterResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data:UserModel
) : Parcelable

@Parcelize
data class BuyResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?
) : Parcelable

@Parcelize
data class TransactionResponse(
    @SerializedName("data")
    val data: ArrayList<TransactionModel>
) : Parcelable
