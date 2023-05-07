package com.example.butterposv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class ThirdAdapter : RecyclerView.Adapter<ThirdAdapter.ViewHolder>() {
//class drinkRecyclerAdapter( var drinkList: ArrayList<DrinkSelections>) : RecyclerView.Adapter<drinkRecyclerAdapter.DrinkViewHolder>() {
class reciept_recycler_adapter(var dataList: List<Pair<String,Int>>) : RecyclerView.Adapter<reciept_recycler_adapter.RecieptViewHolder>(){

    //private var dataList: List<Pair<String, Int>> = emptyList()
    inner class RecieptViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemName :TextView = itemView.findViewById(R.id.reciept_item_name)
        var itemPrice:TextView = itemView.findViewById(R.id.reciept_item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): reciept_recycler_adapter.RecieptViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order, parent, false)
        return RecieptViewHolder(view)
    }

    override fun onBindViewHolder(holder:RecieptViewHolder, position: Int) {
        val data = dataList[position]
        val currentItem = dataList[position]
        //stringValueTextView.text = data.first
        holder.itemName.text = data.first
        holder.itemPrice.text = data.second.toString()

        val myContext = holder.itemView.context


    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun setData(dataList: List<Pair<String, Int>>) {
        this.dataList = dataList
    }


}

