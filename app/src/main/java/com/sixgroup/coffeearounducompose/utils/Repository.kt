package com.sixgroup.coffeearounducompose.utils

import android.content.Context
import com.google.gson.Gson
import com.sixgroup.coffeearounducompose.model.UserModel
import com.sixgroup.coffeearounducompose.utils.Constants.LOGIN_KEY
import com.sixgroup.coffeearounducompose.utils.Constants.SHARED_KEY

object Repository {
    fun checkLogin(context: Context): Boolean {
        return context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
            .getString(LOGIN_KEY, null) != null
    }

    fun getLoginKey(context: Context): UserModel {
        return Gson().fromJson(
            context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
                .getString(LOGIN_KEY, ""), UserModel::class.java
        )
    }

    fun storeLoginKey(context: Context, model: UserModel) {
        context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE).edit().putString(
            LOGIN_KEY, Gson().toJson(model)
        ).apply()
    }

    fun logout(context: Context) {
        context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE).edit().remove(LOGIN_KEY).apply()
    }
}