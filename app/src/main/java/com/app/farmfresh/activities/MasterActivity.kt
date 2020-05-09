package com.app.farmfresh.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.farmfresh.BuildConfig
import com.app.farmfresh.R
import com.google.firebase.FirebaseApp

class MasterActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)

        navController = findNavController(R.id.master_nav_host_fragment)

        when(BuildConfig.FLAVOR)
        {
            "master" ->
            {
                navController.graph = navController.navInflater.inflate(R.navigation.master_nav)
            }

            "delivery" ->
            {
                navController.graph = navController.navInflater.inflate(R.navigation.delivery_boys_nav)
            }

            "manager" ->

            {
                navController.graph = navController.navInflater.inflate(R.navigation.master_nav)
            }

            "shop" ->
            {
                navController.graph = navController.navInflater.inflate(R.navigation.shop_nav)
            }
        }

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController,appBarConfiguration)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        FirebaseApp.initializeApp(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home
            -> {
                navController.navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

