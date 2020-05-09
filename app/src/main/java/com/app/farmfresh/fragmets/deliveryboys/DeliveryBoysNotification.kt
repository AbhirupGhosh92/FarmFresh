package com.app.farmfresh.fragmets.deliveryboys

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.farmfresh.R
import com.app.farmfresh.databinding.DeliveryBoysNotificationFragmentBinding
import com.app.farmfresh.databinding.DeliveryBoysOrdersFragmentBinding

import com.app.farmfresh.viewmodels.deliveryboys.DeliveryBoysNotificationViewModel

class DeliveryBoysNotification : Fragment() {

    private lateinit var viewModel: DeliveryBoysNotificationViewModel
    private lateinit var dataBinding : DeliveryBoysNotificationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.delivery_boys_notification_fragment,container,false)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
