package com.app.farmfresh.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.farmfresh.fragmets.deliveryboys.DeliveryBoysOrderItems
import com.app.farmfresh.fragmets.master.AddAreaFragment
import com.app.farmfresh.fragmets.master.AddCouponsFragment
import com.app.farmfresh.fragmets.master.ShowAreasFragment
import com.app.farmfresh.fragmets.master.ShowCouponsFragment

class CouponPagerAdapter(var fragment : Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =  2

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment
        if(position == 0)
        {
            fragment = AddCouponsFragment()
        }
        else
        {
            fragment = ShowCouponsFragment()
        }
        return fragment
    }

}