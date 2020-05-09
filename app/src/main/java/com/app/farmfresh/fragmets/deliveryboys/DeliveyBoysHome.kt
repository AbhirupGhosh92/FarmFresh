package com.app.farmfresh.fragmets.deliveryboys

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

import com.app.farmfresh.R
import com.app.farmfresh.databinding.DeliveyBoysHomeFragmentBinding
import com.app.farmfresh.factories.ViewModelFactory
import com.app.farmfresh.viewmodels.deliveryboys.DeliveyBoysHomeViewModel

class DeliveyBoysHome : Fragment() {


    private lateinit var viewModel: DeliveyBoysHomeViewModel
    private lateinit var dataBinding : DeliveyBoysHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.delivey_boys_home_fragment,container,false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelFactory().create(DeliveyBoysHomeViewModel::class.java)

    }

}
