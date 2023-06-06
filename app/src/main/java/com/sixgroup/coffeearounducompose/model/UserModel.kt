package com.sixgroup.coffeearounducompose.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("phone_number")
    var phone_number: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("role")
    var role: String,
    @SerializedName("foto")
    var foto: String,
    @SerializedName("token")
    var token: String,
) : Parcelable

@Parcelize
data class RegisterUserModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone_number")
    var phone_number: String,
    @SerializedName("role")
    val role: String = "pembeli"
) : Parcelable
