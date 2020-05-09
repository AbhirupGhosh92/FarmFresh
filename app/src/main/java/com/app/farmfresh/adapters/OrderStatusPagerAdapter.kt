package com.app.farmfresh.adapters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.farmfresh.fragmets.deliveryboys.DeliveryBoysOrders

class OrderStatusPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val CONFIRMED = 0
    private val OUT_FOR_DELIVERY = 1
    private val DELIVERED = 2

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return false
    }

    override fun getCount(): Int  = 3

    override fun getItem(i: Int): Fragment {
        val fragment = DeliveryBoysOrders()
        var bundleId = "type"
        var bundleValue = ""
        when(i)
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

    override fun getPageTitle(position: Int): CharSequence {

        var title = ""

       when(position)
       {
           CONFIRMED ->
           {
               title = "CONFIRMED"
           }

           OUT_FOR_DELIVERY -> {
               title = "OUT FOR DELIVERY"
           }

           DELIVERED -> {
               title = "DELIVERED"
           }
       }

        return title
    }
}