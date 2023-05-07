package com.example.butterposv2

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
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
    }
}