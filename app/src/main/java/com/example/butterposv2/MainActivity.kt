package com.example.butterposv2

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.butterposv2.databinding.ActivityMainBinding
import com.example.butterposv2.ui.ReceiptFragment
import com.example.butterposv2.ui.SupportFragment
import com.example.butterposv2.ui.TotalFragment

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_order_notification"
    private val notificationId = 101

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_order, R.id.navigation_food, R.id.navigation_drinks
            )
        )
        //used for fragment pieces delete if no problems
        /*if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.receipt_fragment_container, ReceiptFragment())
                .replace(R.id.support_fragment_container, SupportFragment())
                .replace(R.id.total_fragment_container, TotalFragment())
                .commitAllowingStateLoss()
        }*/


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        createNotificationChannel()
        val orderButton = findViewById<Button>(R.id.order_button)
        orderButton.setOnClickListener{
            sendNotification()
        }

    }

    private fun createNotificationChannel(){
        //If Android Oreo or above, use REQUIRED notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val name = "Order Status"
            val descriptionText = "Your order has been placed!"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply{
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    // For Versions < Oreo:
    @SuppressLint("MissingPermission")
    private fun sendNotification(){
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Order Status")
            .setContentText("Your order has been placed!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }

}