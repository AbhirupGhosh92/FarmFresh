package com.app.farmfresh.adapters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.farmfresh.fragmets.deliveryboys.DeliveryBoysOrderItems
import com.app.farmfresh.fragmets.deliveryboys.DeliveryBoysOrders

class OrderStatusPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val CONFIRMED = 0
    private val OUT_FOR_DELIVERY = 1
    private val DELIVERED = 2



    override fun getItemCount(): Int =  3

    override fun createFragment(position: Int): Fragment {
        val fragment = DeliveryBoysOrderItems()
        var bundleId = "type"
        var bundleValue = ""
        when(position)
        {
            CONFIRMED ->
            {
                bundleValue = "CONFIRMED"
            }

            OUT_FOR_DELIVERY -> {
                bundleValue = "OUT_FOR_DELIVERY"
            }

            DELIVERED -> {
                bundleValue = "DELIVERED"
            }
        }

        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putString(bundleId,bundleValue)
        }
        return fragment
    }
}