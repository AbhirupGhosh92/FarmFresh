package com.app.farmfresh.fragmets.master

import android.content.Context
import android.database.DatabaseUtils
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.farmfresh.R
import com.app.farmfresh.adapters.AreaPagerAdapter
import com.app.farmfresh.adapters.OrderStatusPagerAdapter
import com.app.farmfresh.databinding.FragmentAreaPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class AreaPagerFragment : Fragment() {

    private val ADD_AREA = 0
    private val SHOW_AREA = 1

    private lateinit var databinding:FragmentAreaPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_area_pager, container, false)
        databinding.orderViewPager.adapter = AreaPagerAdapter(this)


        TabLayoutMediator(databinding.tabLayoutDeliveryOrder,databinding.orderViewPager){tab, position ->

            when(position)
            {
                ADD_AREA -> tab.text = "ADD AREA"
                SHOW_AREA -> tab.text = "SHOW AREA"
            }
        }.attach()
        return databinding.root
    }
}
