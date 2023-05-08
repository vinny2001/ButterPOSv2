package com.example.butterposv2

import android.view.*
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.butterposv2.ui.order.OrderFragment

//class ThirdAdapter : RecyclerView.Adapter<ThirdAdapter.ViewHolder>() {
//class drinkRecyclerAdapter( var drinkList: ArrayList<DrinkSelections>) : RecyclerView.Adapter<drinkRecyclerAdapter.DrinkViewHolder>() {
class reciept_recycler_adapter(var dataList:MutableList<theItem>) : RecyclerView.Adapter<reciept_recycler_adapter.RecieptViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null
    //private var dataList: List<Pair<String, Int>> = emptyList()
    inner class RecieptViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemName :TextView = itemView.findViewById(R.id.reciept_item_name)
        var itemPrice:TextView = itemView.findViewById(R.id.reciept_item_price)






        // Rest of the ViewHolder code...
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): reciept_recycler_adapter.RecieptViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order, parent, false)
        return RecieptViewHolder(view)
    }

    override fun onBindViewHolder(holder:RecieptViewHolder, position: Int) {
        val data = dataList[position]
        val currentItem = dataList[position]
        //stringValueTextView.text = data.first
        holder.itemName.text = data.itemName
        holder.itemPrice.text = data.itemPrice.toString()

        val myContext = holder.itemView.context


    }

    override fun getItemCount(): Int {
        return dataList.size
    }





}

