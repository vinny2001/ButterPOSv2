package com.example.butterposv2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.butterposv2.ui.food.FoodFragment

import kotlin.collections.ArrayList



import kotlin.random.Random

//adapter for the food Recycler View
class foodRecyclerAdapter(val context: FoodFragment,
                          var foodList: ArrayList<AmericanFoods>,
                          var itemList: MutableList<theItem>) : RecyclerView.Adapter<foodRecyclerAdapter.FoodViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    //inner class provides a reference to each particular row in RecyclerView
    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.drinks_name_label)

        val foodPrice: TextView = itemView.findViewById(R.id.drinks_price_label)

        val foodImage: ImageView = itemView.findViewById(R.id.drinks_picture)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = itemList[position]
                    onItemClickListener?.onItemClick(item.itemName, item.itemPrice)
                }
            }
        }

    }

        //
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
            //takes a file and inflates during runtime
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.food_individual_row, parent, false)
            return FoodViewHolder(view)
        }

        //this is where each item is populated
        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
            val currentFood = foodList[position]
            //price is added here. This is random unless we want to implement
            //if(currentFood.foodName == Banana Pancakes){currentFood.mealPrice = NotRandomPrice}
            //for each individual item
            currentFood.mealPrice = Random.nextInt(8, 20)

            holder.foodName.text = "${currentFood.strMeal}"

            holder.foodPrice.text = "$${currentFood.mealPrice}"

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
    fun saveItemToSharedPreferences(item:Item){
        val sharedPreferences = context.getDataFromSharedPreferences("MyPrefs",Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("NameKey", item.toString())
        editor?.apply()
    }



}
