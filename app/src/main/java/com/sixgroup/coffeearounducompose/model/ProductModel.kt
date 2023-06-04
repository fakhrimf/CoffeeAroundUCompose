package com.sixgroup.coffeearounducompose.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nama")
    var nama: String,
    @SerializedName("deskripsi_produk")
    var deskripsi_produk: String,
    @SerializedName("harga")
    var harga: Int,
    @SerializedName("rating")
    var rating: Float,
    @SerializedName("foto")
    var foto: String,
    @SerializedName("available")
    var available: Int,
    @SerializedName("reason")
    var reason: String?,
    @SerializedName("id_toko")
    var id_toko: Int,
) : Parcelable