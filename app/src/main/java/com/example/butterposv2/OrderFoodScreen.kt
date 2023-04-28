package com.example.butterpos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.butterposv2.FoodService
import com.example.butterposv2.R
import com.example.butterposv2.foodRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//CONFIRM SEANS PUSH 4/27
class OrderFoodScreen : AppCompatActivity() {
    val BASE_URL = "https://www.themealdb.com/api/json/v1/1/filter.php?a=American"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_food)//changed to food fragmentfood
        //In OLD PROJECT WAS R.layout.activity_order_food_screen

        val foodList = ArrayList<AmericanFoods>()

        val adapter = foodRecyclerAdapter(foodList)

        val foodRecyclerView = findViewById<RecyclerView>(R.id.food_recycler_view)
        foodRecyclerView.adapter = adapter
        foodRecyclerView.layoutManager = LinearLayoutManager(this)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val foodAPI = retrofitBuilder.create(FoodService::class.java)

        foodAPI.getFoodInfo("a").enqueue(object : Callback<Food> {
            override fun onFailure(call: Call<Food>, t:Throwable) {

            }
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                val foodBody = response.body()
                if (foodBody != null) {
                    foodList.addAll(foodBody.meals)
                }
                adapter.notifyDataSetChanged()
                //userList.addAll(body.results)
                //adapter.notifyDataSetChanged()
            }
        })
    }
}


/*randomUserAPI.getMultipleUserInfoWithNationality(20, "us").enqueue(object :
    Callback<UserData> {

    override fun onFailure(call: Call<UserData>, t: Throwable) {
        Log.d(TAG, "onFailure : $t")
    }

    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
        Log.d(TAG, "onResponse: $response")

        val body = response.body()
        if (body == null){
            Log.w(TAG, "Valid response was not received")
            return
        }

        // The following log messages are just for testing purpose
        Log.d(TAG, ": ${body.results.get(0).name.first}")
        Log.d(TAG, ": ${body.results.get(0).name.last}")
        Log.d(TAG, ": ${body.results.get(0).email}")
        Log.d(TAG, ": ${body.results.get(0).gender}")
        Log.d(TAG, ": ${body.results.get(0).imageUrl.medium}")

        // Update the adapter with the new data
        userList.addAll(body.results)
        adapter.notifyDataSetChanged()
    }

})*/

