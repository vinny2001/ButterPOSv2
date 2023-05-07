package com.example.butterposv2.ui.order

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
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
import com.example.butterposv2.MainActivity
import com.example.butterposv2.R
import com.example.butterposv2.reciept_recycler_adapter
import com.example.butterposv2.ui.theViewModel


class OrderFragment : Fragment() {



    private lateinit var sharedViewModel: theViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: reciept_recycler_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(theViewModel::class.java)



    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        recyclerView = view.findViewById(R.id.reciept_Recycler_View)

        adapter = reciept_recycler_adapter(emptyList()) // Pass an empty list initially

        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        //comment
        //drinksRecyclerView?.adapter = adapter
        //drinksRecyclerView?.layoutManager = LinearLayoutManager(view.context)

        sharedViewModel.getSelectedDataList().observe(viewLifecycleOwner, { dataList ->
            adapter.setData(dataList)
            // Notify the adapter that data has changed
            adapter.notifyDataSetChanged()
        })

        return view
    }

  /*  private fun createNotificationChannel(){
        //If Android Oreo or above, use REQUIRED notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val name = "Order Status"
            val descriptionText = "Your order has been placed!"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply{
                description = descriptionText
            }
            val notificationManager: NotificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    // For Versions < Oreo:

    @SuppressLint("MissingPermission")
    private fun sendNotification(){

        val builder = view?.context?.let {
            NotificationCompat.Builder(it, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Order Status")
                .setContentText("Your order has been placed!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }

        if (builder != null) {
            with(view?.context?.let { NotificationManagerCompat.from(it) }){
                this?.notify(notificationId, builder.build())
            }
        }
    }*/

}

/*
class ThirdFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ThirdAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = ThirdAdapter(emptyList()) // Pass an empty list initially
        recyclerView.adapter = adapter

        sharedViewModel.getSelectedDataList().observe(viewLifecycleOwner, { dataList ->
            adapter.setData(dataList)
            // Notify the adapter that data has changed
            adapter.notifyDataSetChanged()
        })

        return view
    }
}

 */