package com.app.farmfresh.activities

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.farmfresh.BuildConfig
import com.app.farmfresh.FarmFreshApplication
import com.app.farmfresh.R
import com.app.farmfresh.auth.AuthActivity
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.network.ApiModule
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MasterActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var masterViewModel: MasterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        masterViewModel = ViewModelProvider(this)[MasterViewModel::class.java]
        masterViewModel.init(this)

        setContentView(R.layout.activity_master)

        navController = findNavController(R.id.master_nav_host_fragment)

        when(BuildConfig.FLAVOR)
        {
            Constants.master ->
            {
                navController.graph = navController.navInflater.inflate(R.navigation.master_nav)
            }

           Constants.delivery ->
            {
                navController.graph = navController.navInflater.inflate(R.navigation.delivery_boys_nav)
            }

            Constants.manager ->

            {
                navController.graph = navController.navInflater.inflate(R.navigation.manager_nav)
            }

            Constants.shop ->
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

