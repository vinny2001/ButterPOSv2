package com.example.butterposv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//adapter for the food Recycler View
class foodRecyclerAdapter( var foodList: ArrayList<AmericanFoods>) : RecyclerView.Adapter<foodRecyclerAdapter.FoodViewHolder>() {

    //inner class provides a reference to each particular row in RecyclerView
    inner class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val foodName: TextView = itemView.findViewById(R.id.food_name_label)
        //val foodPrice =... create our own price here if we cant grab one from API
        val foodImage: ImageView = itemView.findViewById(R.id.food_picture)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): FoodViewHolder {
        //takes a file and inflates during runtime
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_individual_row, parent, false)
        return FoodViewHolder(view)
    }
    override fun onBindViewHolder(holder: FoodViewHolder, position:Int) {
        val currentFood = foodList[position]
        holder.foodName.text = "${currentFood.strMeal}"
        val myContext = holder.itemView.context

        Glide.with(myContext)
            .load(currentFood.strMealThumb)
            .placeholder(R.drawable.baseline_fastfood_24)//if image doesnt load
            .into(holder.foodImage)


    }
    override fun getItemCount(): Int {

        //BELOW COMMENT COPIED FROM YUSUF IN-CLASS EXAMPLE
        // Return the size of your dataset (invoked by the layout manager)

        return foodList.size
        //also can return whatever size we decide ie. 12 food items
    }


}