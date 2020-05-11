package com.app.farmfresh.fragmets.master

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.app.farmfresh.R
import com.app.farmfresh.adapters.CustomGridAdapter
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.databinding.MasterHomeFragmentBinding
import com.app.farmfresh.repo.models.GridLayoutItem
import com.app.farmfresh.viewmodels.master.MasterHomeViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MasterHome : Fragment() {


    private lateinit var viewModel: MasterHomeViewModel
    private lateinit var dataBinding : MasterHomeFragmentBinding
    private  var firebaseUser : FirebaseUser? = null
    private val RC_SIGN_IN = 11
    private lateinit var navController: NavController

    private var list = arrayListOf(
        GridLayoutItem(R.drawable.ic_stars_black,"Coupons"),
        GridLayoutItem(R.drawable.ic_format_list_numbered,"View Orders"),
        GridLayoutItem(R.drawable.ic_motorcycle_black,"Delivery Boys"),
        GridLayoutItem(R.drawable.ic_playlist_add_black,"All Orders"),
        GridLayoutItem(R.drawable.ic_contact_mail,"Contacts"),
        GridLayoutItem(R.drawable.ic_settings_black,"Settings"),
        GridLayoutItem(R.drawable.ic_add_location,"Areas"),
        GridLayoutItem(R.drawable.ic_image_black,"Banners"),
        GridLayoutItem(R.drawable.ic_airline_seat_individual_suite,"Leaves"),
        GridLayoutItem(R.drawable.ic_business_center,"Area Managers"),
        GridLayoutItem(R.drawable.ic_notifications_active,"Notifications"),
        GridLayoutItem(R.drawable.ic_sync_disabled,"Logout")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseApp.initializeApp(requireContext())
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.master_home_fragment,container,false)
        navController = findNavController()
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MasterHomeViewModel::class.java)
        // TODO: Use the ViewModel

        dataBinding.glMaster.adapter = CustomGridAdapter(list){
            when(it)
            {
                Constants.AREAS ->
                {
                    navController.navigate(R.id.action_masterHome_to_areaPagerFragment)
                }

                Constants.AREA_MANAGERS ->
                {
                    navController.navigate(R.id.action_masterHome_to_areaPagerFragment)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()





    }


}
