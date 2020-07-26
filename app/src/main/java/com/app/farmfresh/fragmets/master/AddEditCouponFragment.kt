package com.app.farmfresh.fragmets.master

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.farmfresh.R
import com.app.farmfresh.databinding.FragmentAddEditCouponBinding

class AddEditCouponFragment : Fragment() {

    private lateinit var dataBinding : FragmentAddEditCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_add_edit_coupon,container,false)
        return dataBinding.root
    }
}