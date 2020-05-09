package com.app.farmfresh.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.app.farmfresh.R

class DeliveryBoysActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delivery_boys_activity)
        navController = findNavController(R.id.delivery_nav_host_fragment)
    }
}
