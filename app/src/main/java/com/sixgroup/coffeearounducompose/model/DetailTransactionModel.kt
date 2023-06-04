package com.sixgroup.coffeearounducompose.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailTransactionModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("id_transaction")
    var id_transaction: Int,
    @SerializedName("id_product")
    var id_product: Int,
) : Parcelable