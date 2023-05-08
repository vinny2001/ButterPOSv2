package com.example.butterposv2

data class Item(val aListOfItems: MutableList<theItem>)
data class theItem(
    val itemName:String,
    val itemPrice:Int
)
