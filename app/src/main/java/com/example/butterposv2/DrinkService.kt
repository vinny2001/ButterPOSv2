package com.example.butterposv2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkService {
    @GET("filter.php")
    fun getDrinkInfo(@Query("a") nonAlcDrinks:String): Call<Drinks>
}