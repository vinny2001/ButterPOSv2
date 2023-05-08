package com.example.butterposv2.ui.order

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.butterposv2.*
import com.example.butterposv2.ui.theViewModel


class OrderFragment : Fragment() {



   lateinit var sharedPreferences:SharedPreferences
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: reciept_recycler_adapter

    val PREF_NAME = "MyPrefs"
    val NAME_KEY = "Name"
    val VALUE_KEY = "Value"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_order, container, false)

        recyclerView = view.findViewById(R.id.reciept_Recycler_View)

        //adapter = reciept_recycler_adapter(emptyList()) // Pass an empty list initially

        val dataList = mutableListOf<theItem>()
        //grab info from shared preferences
        sharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val name = sharedPreferences.getString(NAME_KEY, "") ?: ""
        val value = sharedPreferences.getInt(VALUE_KEY, 0)
        //create an Iem object
        val item = theItem(name, value)
        dataList.add(item)

        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(view.context)

        val adapter = reciept_recycler_adapter(dataList)
        recyclerView.adapter = adapter


            // Notify the adapter that data has changed
            adapter.notifyDataSetChanged()
        return view
        }


    }

