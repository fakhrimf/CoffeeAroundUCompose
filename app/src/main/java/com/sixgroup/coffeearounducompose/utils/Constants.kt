package com.sixgroup.coffeearounducompose.utils

object Constants {
    // API URL
    private const val NGROK_URL = "https://4a9b-113-11-180-17.ngrok-free.app/"
    const val BASE_URL = NGROK_URL+"api/"
    const val IMAGE_URL = NGROK_URL+"public/Image/"

    // INTENTS
    const val PRODUCT_DETAIL = "productdetailintent"
    const val TOKO_DETAIL = "tokodetailintent"
    const val IMAGE_TYPE = "image/*"

    // SHARED PREFERENCES
    const val SHARED_KEY = "sharedprefscoffee"
    const val LOGIN_KEY = "sp_login"

    // REQUEST CODES
    const val CAMERA_REQUEST_CODE = 34
    const val STORAGE_REQUEST_CODE = 69
    const val IMAGE_REQUEST_CODE = 71

    // UTILITIES
    const val MAXIMAL_SIZE = 1000000
}