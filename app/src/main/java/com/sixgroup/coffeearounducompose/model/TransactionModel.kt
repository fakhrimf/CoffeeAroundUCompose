package com.sixgroup.coffeearounducompose.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionModel(
    @SerializedName("transaction_id")
    var id: Int,
    @SerializedName("harga_total")
    var harga_total: Int,
    @SerializedName("user_id")
    var id_user: Int,
    @SerializedName("toko_id")
    var id_toko: Int,
    @SerializedName("nama_product")
    var productName: String,
    @SerializedName("deskripsi_produk")
    var productDesc: String,
    @SerializedName("harga")
    var harga: Int,
    @SerializedName("foto")
    var foto: String,
    @SerializedName("nama_toko")
    var nama_toko: String,
    @SerializedName("product_id")
    var id_produk: Int,
    @SerializedName("rating")
    var rating: Float
) : Parcelable