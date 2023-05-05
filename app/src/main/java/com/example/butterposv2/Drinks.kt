package com.example.butterposv2

data class Drinks(
    val drinks:List<DrinkSelections>
)

data class DrinkSelections(
    val strDrink: String,
    val strDrinkThumb: String,
    var drinkPrice: Int //drinkPrice is not provided by API
)