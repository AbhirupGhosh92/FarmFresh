package com.app.farmfresh.fragmets.deliveryboys

import android.database.DatabaseUtils
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.app.farmfresh.R
import com.app.farmfresh.adapters.OrderStatusPagerAdapter
import com.app.farmfresh.databinding.DeliveryBoysOrdersFragmentBinding
import com.app.farmfresh.viewmodels.deliveryboys.DeliveryBoysOrdersViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DeliveryBoysOrders : Fragment() {

    private val CONFIRMED = 0
    private val OUT_FOR_DELIVERY = 1
    private val DELIVERED = 2

    private lateinit var viewModel: DeliveryBoysOrdersViewModel
    private lateinit var dataBinding: DeliveryBoysOrdersFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dataBinding = DataBindingUtil.inflate(inflater,R.layout.delivery_boys_orders_fragment,container,false)
        dataBinding.orderViewPager.adapter = OrderStatusPagerAdapter(this)


        TabLayoutMediator(dataBinding.tabLayoutDeliveryOrder,dataBinding.orderViewPager){tab, position ->

            when(position)
            {
                CONFIRMED -> tab.text = "CONFIRMED"
                OUT_FOR_DELIVERY -> tab.text = "OUT FOR DELIVERY"
                DELIVERED -> tab.text = "DELIVERED"
            }
        }.attach()

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}
