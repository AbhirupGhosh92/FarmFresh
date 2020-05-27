package com.app.farmfresh.fragmets.deliveryboys

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
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.app.farmfresh.R
import com.app.farmfresh.databinding.DeliveyBoysHomeFragmentBinding
import com.app.farmfresh.viewmodels.deliveryboys.DeliveyBoysHomeViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class DeliveyBoysHome : Fragment() {


    private lateinit var viewModel: DeliveyBoysHomeViewModel
    private lateinit var dataBinding : DeliveyBoysHomeFragmentBinding
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
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.delivey_boys_home_fragment,container,false)
        navController = findNavController()
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DeliveyBoysHomeViewModel::class.java]

        dataBinding.llNotification.setOnClickListener {
            navController.navigate(R.id.action_deliveyBoysHome_to_deliveryBoysNotification)
        }

        dataBinding.llOrders.setOnClickListener {
            navController.navigate(R.id.action_deliveyBoysHome_to_deliveryBoysOrders)
        }

        dataBinding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startAuth()
        }

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
                    Toast.makeText(requireContext(),response?.error.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
