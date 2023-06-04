package com.sixgroup.coffeearounducompose.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("harga_total")
    var harga_total: Int,
    @SerializedName("id_user")
    var id_user: Int,
    @SerializedName("id_toko")
    var id_toko: Int,
) : Parcelable