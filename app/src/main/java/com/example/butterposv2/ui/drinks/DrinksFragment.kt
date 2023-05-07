package com.example.butterposv2.ui.drinks

import android.annotation.SuppressLint
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


class DrinksFragment : Fragment() {
    // (Vinny): Changed URL for now to avoid runtime error
    val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    private lateinit var sharedViewModel: theViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(theViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {



        val view = inflater.inflate(R.layout.fragment_drinks, container, false)

        val drinkList = ArrayList<DrinkSelections>()

        val adapter = drinkRecyclerAdapter(drinkList)

        val drinksRecyclerView = view.findViewById<RecyclerView>(R.id.drinks_recycler_view)

        //Setting up linear layout manager and attaching adapter... error here(?)
        // * Put adapter after layout inflater, but adapter STILL not attached (RUNTIME ERROR)


        drinksRecyclerView?.adapter = adapter
        drinksRecyclerView?.layoutManager = LinearLayoutManager(view.context)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val drinkAPI = retrofitBuilder.create(DrinkService::class.java)

        drinkAPI.getDrinkInfo("Non_Alcoholic").enqueue(object : Callback<Drinks> {
            override fun onFailure(call: Call<Drinks>, t:Throwable) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {
                val drinkBody = response.body()
                if (drinkBody != null) {
                    drinkList.addAll(drinkBody.drinks)
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
    fun onItemDoubleClick(stringValue: String, intValue: Int) {
        // Handle double click event for grabbing information from the first fragment
    }

}