package com.app.farmfresh.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.farmfresh.fragmets.deliveryboys.DeliveryBoysOrderItems
import com.app.farmfresh.fragmets.master.AddAreaFragment
import com.app.farmfresh.fragmets.master.ShowAreasFragment

class AreaPagerAdapter(var fragment : Fragment) : FragmentStateAdapter(fragment) {

    private val ADD_AREA = 0
    private val SHOW_AREA = 1



    override fun getItemCount(): Int =  2

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment
       if(position == 0)
        {
            fragment = AddAreaFragment()
        }
        else
       {
           fragment = ShowAreasFragment()
       }
        return fragment
    }

}