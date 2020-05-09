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

class DeliveryBoysOrders : Fragment() {


    private lateinit var viewModel: DeliveryBoysOrdersViewModel
    private lateinit var dataBinding: DeliveryBoysOrdersFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dataBinding = DataBindingUtil.inflate(inflater,R.layout.delivery_boys_orders_fragment,container,false)
        dataBinding.orderViewPager.adapter = OrderStatusPagerAdapter(parentFragmentManager)
        dataBinding.tabLayoutDeliveryOrder.setupWithViewPager(dataBinding.orderViewPager)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeliveryBoysOrdersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
