package com.example.butterposv2


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.random.Random

//adapter for the drink Recycler View
class drinkRecyclerAdapter( var drinkList: ArrayList<DrinkSelections>) : RecyclerView.Adapter<drinkRecyclerAdapter.DrinkViewHolder>() {

    //inner class provides a reference to each particular row in RecyclerView
    inner class DrinkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val drinkName: TextView = itemView.findViewById(R.id.drinks_name_label)
        val drinkPrice: TextView = itemView.findViewById(R.id.drinks_price_label)
        val drinkImage: ImageView = itemView.findViewById(R.id.drinks_picture)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): DrinkViewHolder {
        //takes a file and inflates during runtime
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drinks_individual_row, parent, false)
        return DrinkViewHolder(view)
    }
    override fun onBindViewHolder(holder: DrinkViewHolder, position:Int) {
        val currentDrink = drinkList[position]
        holder.drinkName.text = "${currentDrink.strDrink}"
        currentDrink.drinkPrice = Random.nextInt(5,10)
        holder.drinkPrice.text = "$${currentDrink.drinkPrice}"
        val myContext = holder.itemView.context

        Glide.with(myContext)
            .load(currentDrink.strDrinkThumb)
            .placeholder(R.drawable.baseline_fastfood_24)//if image doesnt load
            .into(holder.drinkImage)


    }
    override fun getItemCount(): Int {

        //BELOW COMMENT COPIED FROM YUSUF IN-CLASS EXAMPLE
        // Return the size of your dataset (invoked by the layout manager)

        return drinkList.size
        //dynamic size based on the list
    }


}