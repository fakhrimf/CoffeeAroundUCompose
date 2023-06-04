package com.sixgroup.coffeearounducompose.model

object DummyModel {
    fun generateDummyProducts(amount: Int): ArrayList<ProductModel> {
        val prods = ArrayList<ProductModel>()
        for (i in 0 until amount) {
            prods.add(
                ProductModel(
                    id = i,
                    nama = "Produk $i",
                    deskripsi_produk =  "Deskripsi Produk $i",
                    harga = 1_000 * i,
                    rating = 5.0.toFloat(),
                    foto = "https://img.freepik.com/free-photo/cup-coffee-with-heart-drawn-foam_1286-70.jpg?1&w=996&t=st=1685785985~exp=1685786585~hmac=648f892bd489799ff0af89c50d2c600b9364d3aba785496a0afbfe17e9388835",
                    available = 1,
                    id_toko = i,
                    reason = null
                )
            )
        }
        return prods
    }

    fun generateDummyTokos(amount: Int): ArrayList<TokoModel> {
        val tokos = ArrayList<TokoModel>()
        for (i in 0 until amount) {
            tokos.add(
                TokoModel(
                    i,
                    "Toko $i",
                    "Jl. Telekomunikasi No. $i",
                    0.0.toFloat(),
                    0.0.toFloat(),
                    "https://cdn.discordapp.com/attachments/775027234096414720/1111591176634630174/backdrop.png",
                    i
                )
            )
        }
        return tokos
    }
}