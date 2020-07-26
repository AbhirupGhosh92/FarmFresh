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

class ManagerHome : Fragment() {

    private lateinit var viewModel: ManagerHomeViewModel
    private lateinit var dataBinding : ManagerHomeFragmentBinding
    private lateinit var navController: NavController
    private val RC_SIGN_IN = 11

    private  var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(requireContext());

    }

    override fun onResume() {
        super.onResume()

        if(FirebaseAuth.getInstance().currentUser == null)
        {
            startAuth()
        }else
        {
            firebaseUser = FirebaseAuth.getInstance().currentUser
        }

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


    }

    fun startAuth()
    {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().setAllowNewAccounts(false)
                .build())
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode)
        {
            RC_SIGN_IN -> {
                val response = IdpResponse.fromResultIntent(data)

                if (resultCode == Activity.RESULT_OK) {
                    // Successfully signed in
                    firebaseUser = FirebaseAuth.getInstance().currentUser
                    // ...
                } else {
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                    // ...



                    if(response?.error!=null)
                        Toast.makeText(requireContext(),response?.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
