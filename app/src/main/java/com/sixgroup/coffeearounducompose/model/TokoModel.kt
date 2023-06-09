package com.sixgroup.coffeearounducompose.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokoModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nama")
    var nama: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("latitude")
    var lat: String,
    @SerializedName("longitude")
    var lon: String,
    @SerializedName("icon_url")
    var icon_url: String,
    @SerializedName("id_user")
    var id_user: Int,
) : Parcelable