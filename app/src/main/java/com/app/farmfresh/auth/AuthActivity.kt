package com.app.farmfresh.auth

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.app.farmfresh.BuildConfig
import com.app.farmfresh.R
import com.app.farmfresh.activities.MasterActivity
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.databinding.AuthLayoutBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {

    private lateinit var firebaseUser: FirebaseUser
    private val RC_SIGN_IN = 11
    private val RC_SIGN_IN_NON_DEFAULT = 12
    private lateinit var dataBindinng : AuthLayoutBinding
    private lateinit var gso : GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        dataBindinng = DataBindingUtil.setContentView(this, R.layout.auth_layout)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                window?.statusBarColor = resources?.getColor(R.color.colorAccentLight,null)!!

        }

        dataBindinng.btnRetry.setOnClickListener {
            startAuth()
        }

    }


    override fun onStart() {
        super.onStart()

        startAuth()

    }


    fun startAuth()
    {
        dataBindinng.btnRetry.visibility = View.GONE

        if(BuildConfig.FLAVOR == Constants.master) {

            val providers = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder()
                    .build()
            )
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN
            )

        }

        else if(BuildConfig.FLAVOR == Constants.shop) {

            dataBindinng.btnRetry.visibility = View.GONE
            val providers = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder()
                    .build()
            )
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN
            )

        }

        else if(BuildConfig.FLAVOR == Constants.delivery)
        {

            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this,gso)

            var signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN_NON_DEFAULT)

        }


        else if(BuildConfig.FLAVOR == Constants.manager)
        {
            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this,gso)

            var signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN_NON_DEFAULT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode)
        {
            RC_SIGN_IN -> {
                val response = IdpResponse.fromResultIntent(data)

                if (resultCode == Activity.RESULT_OK) {
                    // Successfully signed in
                    // ...


                    var intent = Intent(this,MasterActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                    // ...

                    if(response?.error!=null)
                        Toast.makeText(this,response?.error?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()

                   dataBindinng.btnRetry.visibility = View.VISIBLE
                }
            }

            RC_SIGN_IN_NON_DEFAULT ->
            {
                val response = IdpResponse.fromResultIntent(data)

                if (resultCode == Activity.RESULT_OK) {
                    // Successfully signed in
                    // ...

                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    try {
                        // Google Sign In was successful, authenticate with Firebase
                        val account = task.getResult(ApiException::class.java)!!
                        Log.d("auth", "firebaseAuthWithGoogle:" + account.id)
                        firebaseAuthWithGoogle(account.idToken!!)
                    } catch (e: ApiException) {
                        // Google Sign In failed, update UI appropriately
                        Log.w("auth", "Google sign in failed", e)
                        // ...
                    }

                    var intent = Intent(this,MasterActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                    // ...

                    if(response?.error!=null)
                        Toast.makeText(this,response?.error?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()

                    dataBindinng.btnRetry.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("auth", "signInWithCredential:success")
                    var intent = Intent(this,MasterActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("auth", "signInWithCredential:failure", task.exception)
                    // ...
                   Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()

                }

                // ...
            }
    }

}