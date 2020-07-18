package com.app.farmfresh.fragmets.master

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.farmfresh.R
import com.app.farmfresh.adapters.AreaPagerAdapter
import com.app.farmfresh.adapters.CouponPagerAdapter
import com.app.farmfresh.databinding.FragmentAreaPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class CouponPagerFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val ADD_COUPON = 0
    private val SHOW_COUPON = 1

    private lateinit var databinding: FragmentAreaPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_area_pager, container, false)
        databinding.orderViewPager.adapter = CouponPagerAdapter(this)


        TabLayoutMediator(databinding.tabLayoutDeliveryOrder,databinding.orderViewPager){tab, position ->

            when(position)
            {
                ADD_COUPON -> tab.text = "ADD COUPON"
                SHOW_COUPON -> tab.text = "SHOW COUPON"
            }
        }.attach()
        return databinding.root
    }
}