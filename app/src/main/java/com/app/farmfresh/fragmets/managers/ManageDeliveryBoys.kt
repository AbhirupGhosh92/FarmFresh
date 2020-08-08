package com.app.farmfresh.fragmets.managers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.farmfresh.R
import com.app.farmfresh.adapters.GenericPagerAdapter
import com.app.farmfresh.databinding.FragmentManageDeliveryBoysBinding
import com.google.android.material.tabs.TabLayoutMediator

class ManageDeliveryBoys : Fragment() {

    private lateinit var dataBinding : FragmentManageDeliveryBoysBinding
    private val titleList = listOf("Manage Delivery Boys","Assign Orders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_manage_delivery_boys,container,false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataBinding.orderViewPager.adapter = GenericPagerAdapter(this, listOf(ManagerAddDeliveryBoys(),AssignOrders()))

        TabLayoutMediator(dataBinding.tabLayoutDeliveryOrder,dataBinding.orderViewPager){tab, position ->
            tab.text = titleList[position]
        }.attach()
    }
}