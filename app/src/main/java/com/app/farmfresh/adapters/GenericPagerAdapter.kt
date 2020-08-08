package com.app.farmfresh.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter

class GenericPagerAdapter(fragment : Fragment,var fragmentList: List<Fragment>) : FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}