package com.app.farmfresh.fragmets.master

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.farmfresh.R
import com.app.farmfresh.adapters.ShowAreaAdapter
import com.app.farmfresh.adapters.ShowCouponAdapter
import com.app.farmfresh.databinding.FragmentShowAreasBinding
import com.app.farmfresh.databinding.FragmentShowCouponsBinding
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.CouponModel
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel
import com.app.farmfresh.viewmodels.master.CouponFragmentViewModel

class ShowCouponsFragment : Fragment() {

    private var couponList = mutableListOf<CouponModel>()
    private lateinit var dataBinding : FragmentShowCouponsBinding
    private lateinit var viewModel : CouponFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_coupons,container,false)

        dataBinding.rvCouponData.adapter = ShowCouponAdapter(findNavController(),couponList)
        dataBinding.rvCouponData.itemAnimator = DefaultItemAnimator()
        dataBinding.rvCouponData.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvCouponData.adapter?.notifyDataSetChanged()

        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CouponFragmentViewModel::class.java)
        viewModel.getCouponList()?.observe(viewLifecycleOwner, Observer {

            if(it.isNullOrEmpty().not()) {

                dataBinding.rvCouponData.visibility = View.VISIBLE
                dataBinding.tvNoData.visibility = View.GONE

                couponList.clear()
                couponList.addAll(it)
                dataBinding.rvCouponData.adapter?.notifyDataSetChanged()
            }
            else
            {
                dataBinding.rvCouponData.visibility = View.GONE
                dataBinding.tvNoData.visibility = View.VISIBLE
            }
        })
    }
}