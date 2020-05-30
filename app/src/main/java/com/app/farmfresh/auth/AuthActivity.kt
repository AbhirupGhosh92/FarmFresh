package com.app.farmfresh.auth

import `in`.aabhasjindal.otptextview.OTPListener
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.icu.util.TimeUnit
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.*
import com.app.farmfresh.BuildConfig
import com.app.farmfresh.FarmFreshApplication
import com.app.farmfresh.R
import com.app.farmfresh.activities.MasterActivity
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.databinding.AuthLayoutBinding
import com.app.farmfresh.models.CheckAccessModel
import com.app.farmfresh.models.MobileNumberModel
import com.app.farmfresh.utils.Utils
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*

class AuthActivity : AppCompatActivity(),ViewModelStoreOwner {

    private  var firebaseUser: FirebaseUser? = null
    private val RC_SIGN_IN = 11
    private val RC_SIGN_IN_NON_DEFAULT = 12
    private lateinit var dataBindinng : AuthLayoutBinding
    private lateinit var gso : GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private var auth = FirebaseAuth.getInstance()
    private val DEFAULT = 0
    private val ENTER_MOBILE = 1
    private val ENTER_OTP = 2
    private val RESET = 3
    private var verificationId = ""
    private  var code = ""
    private var mContext = this
    private lateinit var authViewModel : AuthViewModel


    private val  callbacks =  object  : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
           code = p0.smsCode.toString()
        }

        override fun onVerificationFailed(p0: FirebaseException) {


            Snackbar.make(dataBindinng.root,p0?.localizedMessage,Snackbar.LENGTH_LONG).show()
            manageUi(RESET)
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            verificationId = p0
            manageUi(ENTER_OTP)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        dataBindinng = DataBindingUtil.setContentView(this, R.layout.auth_layout)


        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                window?.statusBarColor = resources?.getColor(R.color.colorAccentLight,null)!!

        }

        dataBindinng.btnRetry.setOnClickListener {
            requestOpp()
        }

        firebaseUser = FirebaseAuth.getInstance().currentUser

        if( firebaseUser!=null && firebaseUser?.email.isNullOrEmpty().not())
        {
            dataBindinng.tvEmailId.text = "Welcome\n${firebaseUser?.email}"
        }

        dataBindinng.btnReenterMobile.setOnClickListener {
            dataBindinng.ccp.visibility = View.VISIBLE
            dataBindinng.edtEnterMobile.visibility = View.VISIBLE
            dataBindinng.btnRetry.visibility = View.GONE
            dataBindinng.btnReenterMobile.visibility = View.GONE
            dataBindinng.otpView.visibility = View.GONE
        }
        dataBindinng.otpView.otpListener = object : OTPListener
        {
            override fun onInteractionListener()
            {

            }

            override fun onOTPComplete(otp: String)
            {
                Utils.hideKeyboard(this@AuthActivity,dataBindinng.otpView)

               if(otp == code)
               {

                   authViewModel.addMonbileNumber(MobileNumberModel(
                       FirebaseAuth.getInstance().uid.toString()
                       ,dataBindinng.ccp.selectedCountryCodeWithPlus+dataBindinng.edtEnterMobile.text.toString()
                   ))
                       .observe(mContext as LifecycleOwner, Observer {
                           if(it.status == 200)
                           {
                               var intent = Intent(this@AuthActivity,MasterActivity::class.java)
                               finish()
                               startActivity(intent)
                           }
                           else
                           {
                               Snackbar.make(dataBindinng.root,it.responseMessage,Snackbar.LENGTH_LONG).show()
                           }
                       })



               }
                else
               {
                   Snackbar.make(dataBindinng.root,"Wrong Code Entered",Snackbar.LENGTH_LONG).show()

                   //TODO(Resend)

                   //TODO(Change mobile number)
               }
            }
        }


        dataBindinng.edtEnterMobile.addTextChangedListener{
            if(it?.toString()?.length == 10)
            {

                requestOpp()
            }
        }


    }


    private fun requestOpp()
    {
        Utils.hideKeyboard(this,dataBindinng.edtEnterMobile)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            dataBindinng.ccp.selectedCountryCodeWithPlus+dataBindinng.edtEnterMobile.text.toString(), // Phone number to verify
            60, // Timeout duration
            java.util.concurrent.TimeUnit.SECONDS, // Unit of timeout
            this, callbacks) // OnVerificationStateChangedCallbacks
    }


    override fun onStart() {
        super.onStart()


        startAuth()

    }

    private fun manageUi(case : Int = DEFAULT)
    {

        when(case)
        {
            DEFAULT ->
            {
                dataBindinng.btnRetry.visibility = View.GONE
                dataBindinng.btnReenterMobile.visibility = View.GONE
                dataBindinng.ccp.visibility = View.GONE
                dataBindinng.edtEnterMobile.visibility = View.GONE
                dataBindinng.otpView.visibility = View.GONE
            }

            RESET ->
            {
                dataBindinng.btnRetry.visibility = View.GONE
                dataBindinng.btnReenterMobile.visibility = View.GONE
                dataBindinng.ccp.visibility = View.GONE
                dataBindinng.edtEnterMobile.visibility = View.GONE
                dataBindinng.otpView.visibility = View.GONE
                dataBindinng.btnRetry.visibility = View.VISIBLE
            }

            ENTER_MOBILE ->
            {
                dataBindinng.btnRetry.visibility = View.GONE
                dataBindinng.btnReenterMobile.visibility = View.GONE
                dataBindinng.ccp.visibility = View.VISIBLE
                dataBindinng.edtEnterMobile.visibility = View.VISIBLE
                dataBindinng.otpView.visibility = View.GONE

            }

            ENTER_OTP -> {
                dataBindinng.btnRetry.visibility = View.GONE
                dataBindinng.btnReenterMobile.visibility = View.GONE
                dataBindinng.ccp.visibility = View.GONE
                dataBindinng.edtEnterMobile.visibility = View.GONE
                dataBindinng.otpView.visibility = View.VISIBLE

            }

        }


    }



    fun startAuth()
    {
       manageUi()

        if(BuildConfig.FLAVOR == Constants.master) {

            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this,gso)

            var signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN_NON_DEFAULT)

        }

        else if(BuildConfig.FLAVOR == Constants.shop) {

            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this,gso)
            var signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN_NON_DEFAULT)

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


//                    var intent = Intent(this,MasterActivity::class.java)
//                    finish()
//                    startActivity(intent)

                } else {
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                    // ...

                    if(response?.error!=null)
                        Toast.makeText(this,response?.error?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()

                   dataBindinng.btnRetry.visibility = View.VISIBLE
                    dataBindinng.btnReenterMobile.visibility = View.VISIBLE
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
                        {

                            dataBindinng.tvEmailId.text = "Welcome\n${account.email}"

                            authViewModel.checkAccess(
                                CheckAccessModel(FirebaseAuth.getInstance().uid.toString())
                            )
                                .observe(this as LifecycleOwner, Observer {

                                    if (it.data.accessGranted && it.data.mobileAdded) {
                                        var intent = Intent(this,MasterActivity::class.java)
                                        var bundle = Bundle()
                                        bundle.putSerializable("accessData",it.data)
                                        finish()
                                        startActivity(intent)
                                    } else if (it.data.accessGranted && it.data.mobileAdded.not()) {
                                        manageUi(ENTER_MOBILE)
                                    } else if (it.data.accessGranted.not()) {
                                        Snackbar.make(
                                            dataBindinng.root,
                                            "Access Not Granted",
                                            Snackbar.LENGTH_INDEFINITE
                                        ).show()
                                    }


                                })
                        }


                    } catch (e: ApiException) {
                        // Google Sign In failed, update UI appropriately
                        Log.w("auth", "Google sign in failed", e)
                        // ...
                    }




                } else {
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                    // ...

                    if(response?.error!=null)
                        Toast.makeText(this,response?.error?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()

                    dataBindinng.btnRetry.visibility = View.VISIBLE
                    dataBindinng.btnReenterMobile.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String,snippet : (user : FirebaseUser?) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("auth", "signInWithCredential:success")
//                    var intent = Intent(this,MasterActivity::class.java)
//                    finish()
//                    startActivity(intent)
                    snippet.invoke(task.result?.user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("auth", "signInWithCredential:failure", task.exception)
                    // ...
                   Toast.makeText(this,task.exception?.localizedMessage,Toast.LENGTH_SHORT).show()

                }

                // ...
            }
    }

}