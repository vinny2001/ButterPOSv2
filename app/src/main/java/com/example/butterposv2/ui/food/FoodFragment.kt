package com.example.butterposv2.ui.food

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.butterposv2.*
import com.example.butterposv2.ui.theViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FoodFragment : Fragment() {
    // (Vinny): Changed URL for now to avoid runtime error
    val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private lateinit var sharedViewModel: theViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(theViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_food, container, false)

        val foodList = ArrayList<AmericanFoods>()

        val adapter = foodRecyclerAdapter(foodList)

        val foodRecyclerView = view.findViewById<RecyclerView>(R.id.food_recycler_view)

        //Setting up linear layout manager and attaching adapter... error here(?)
        // * Put adapter after layout inflater, but adapter STILL not attached (RUNTIME ERROR)


        foodRecyclerView?.adapter = adapter
        foodRecyclerView?.layoutManager = LinearLayoutManager(view.context)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val foodAPI = retrofitBuilder.create(FoodService::class.java)

        foodAPI.getFoodInfo("American").enqueue(object : Callback<Food> {
            override fun onFailure(call: Call<Food>, t:Throwable) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                val foodBody = response.body()
                if (foodBody != null) {
                    foodList.addAll(foodBody.meals)
                    adapter.notifyDataSetChanged()
                }
            }
        })

        // Inflate the layout for this fragment
        return view
    }
    fun onItemClick(stringValue: String, intValue: Int) {
        val dataList = listOf(Pair(stringValue, intValue))
        sharedViewModel.setSelectedDataList(dataList)
    }
}


/*
class FirstFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // ...
    }

    override fun onItemClick(stringValue: String, intValue: Int) {
        val dataList = listOf(Pair(stringValue, intValue))
        sharedViewModel.setSelectedDataList(dataList)
        // ...
    }
}

*/