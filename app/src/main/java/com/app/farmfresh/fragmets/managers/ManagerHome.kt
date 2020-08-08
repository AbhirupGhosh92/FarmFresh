package com.app.farmfresh.fragmets.managers

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.app.farmfresh.R
import com.app.farmfresh.databinding.DeliveyBoysHomeFragmentBinding
import com.app.farmfresh.databinding.ManagerHomeFragmentBinding
import com.app.farmfresh.viewmodels.deliveryboys.DeliveyBoysHomeViewModel
import com.app.farmfresh.viewmodels.managers.ManagerHomeViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ManagerHome : Fragment(),View.OnClickListener {

    private lateinit var viewModel: ManagerHomeViewModel
    private lateinit var dataBinding : ManagerHomeFragmentBinding
    private lateinit var navController: NavController


    private  var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.manager_home_fragment,container,false)
        navController = findNavController()
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ManagerHomeViewModel::class.java]
        dataBinding.llDelivery.setOnClickListener(this)
        dataBinding.llArea.setOnClickListener(this)
        dataBinding.llItems.setOnClickListener(this)
        dataBinding.llNotification.setOnClickListener(this)
        dataBinding.llOrders.setOnClickListener(this)
        dataBinding.llDisputes.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            dataBinding.llDelivery.id -> {
                findNavController().navigate(R.id.action_managerHome_to_manageDeliveryBoys)
            }

            dataBinding.llArea.id -> {
                findNavController().navigate(R.id.action_managerHome_to_manageAreas)
            }

            dataBinding.llItems.id -> {
                findNavController().navigate(R.id.action_managerHome_to_manageItems)
            }

            dataBinding.llNotification.id -> {
                findNavController().navigate(R.id.action_managerHome_to_manageNotifications)
            }

            dataBinding.llOrders.id -> {
                findNavController().navigate(R.id.action_managerHome_to_manageOrders)
            }

            dataBinding.llDisputes.id -> {
                findNavController().navigate(R.id.action_managerHome_to_manageDisputes)
            }
        }
    }


}
